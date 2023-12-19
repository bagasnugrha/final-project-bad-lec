package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AppointmentData;
import model.User;
import util.Connect;

public class AppointmentListController {

	Connect connect = Connect.getInstance();
	ObservableList<AppointmentData> appointmentList = FXCollections.observableArrayList();;
	ResultSet resultSet;
	User user;

    public void getAppointmentData(){
        String userID = "US001"; //Tolong diganti 

        System.out.println(userID);

        try {
            String query = "SELECT appointment.appointmentID, doctor.doctorName, appointment.appointmentDate, appointment.timeSlot " +
                    "FROM appointment " +
                    "JOIN doctor ON appointment.doctorID = doctor.doctorID " +
                    "WHERE appointment.userID = ?";

            connect.ps = connect.getPreparedStatement(query);
            connect.ps.setString(1, userID);
            connect.rs = connect.ps.executeQuery();

            while (connect.rs.next()) {
                String appointmentID = connect.rs.getString("appointmentID");
                String doctorName = connect.rs.getString("doctorName");
                Date appointmentDate = connect.rs.getDate("appointmentDate");
                Float timeSlot = connect.rs.getFloat("timeSlot");

                System.out.println(appointmentID + " " + doctorName + " " + appointmentDate + " " + timeSlot);
                
                AppointmentData appointmentItem = new AppointmentData(appointmentID, doctorName, appointmentDate, timeSlot);
                appointmentList.add(appointmentItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connect.closePreparedStatement();
        }
    }
	
    public ObservableList<AppointmentData> getAppointmentList() {
        return appointmentList;
    }
}
