package com.example.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.example.model.Appointment;
import com.example.model.User;
import com.example.util.Connect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AppointmentListController {
    
    Connect connect = Connect.getInstance();
	ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();;
	ResultSet resultSet;
	User user;

    public void getAppointmentData(){

        user = LoginController.user; // ambil si user dari login controller

        String userID = user.getUserID(); // ambil idnya, sesuai sama yg login

        try {
            String query = "SELECT appointment.appointmentID, doctor.doctorName, appointment.appointmentDate, appointment.timeSlot " +
                    "FROM appointment " +
                    "JOIN doctor ON appointment.doctorID = doctor.doctorID " +
                    "WHERE appointment.userID = ?";

            connect.ps = connect.getPreparedStatement(query);
            connect.ps.setString(1, userID);
            connect.rs = connect.ps.executeQuery();

            // System.out.println(userID);

            while (connect.rs.next()) {
                String appointmentID = connect.rs.getString("appointmentID");
                String doctorName = connect.rs.getString("doctorName");
                Date appointmentDate = connect.rs.getDate("appointmentDate");
                Float timeSlot = connect.rs.getFloat("timeSlot");
                
                Appointment appointmentItem = new Appointment(appointmentID, doctorName, appointmentDate, timeSlot);
                appointmentList.add(appointmentItem);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connect.closePreparedStatement();
        }
    }
	
    public ObservableList<Appointment> getAppointmentList() {
        return appointmentList;
    }
}
