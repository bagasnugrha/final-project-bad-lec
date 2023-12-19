package com.example.controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.util.Connect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AppointmentFormController {

	Connect connect = Connect.getInstance();
	
	public ObservableList<String> getDoctorNames() {
        ObservableList<String> doctorNames = FXCollections.observableArrayList();

        try {
            String query = "SELECT doctorName FROM doctor";
            connect.execQuery(query);

            while (connect.rs.next()) {
                String doctorName = connect.rs.getString("doctorName");
                doctorNames.add(doctorName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctorNames;
    }
	
	public String getDoctorID(String doctorName) {
        String doctorID = null;
        System.out.println("Method getDoctorID ke panggil");

        try {
            String query = "SELECT doctorID FROM doctor WHERE doctorName = ?";
            PreparedStatement ps = connect.getPreparedStatement(query);
            ps.setString(1, doctorName);
            connect.rs = ps.executeQuery();

            if (connect.rs.next()) {
                doctorID = connect.rs.getString("doctorID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctorID;
    }

	public void insertAppointmentToDatabase(Date appointmentDate, String userID, String doctorID, Float timeSlot) {
		String query = "INSERT INTO appointment (appointmentID, appointmentDate, userID, doctorID, timeSlot) VALUES (?, ?, ?, ?, ?)";
		
		System.out.println("Method insertAppointmentToDatabase ke panggil");
		
        PreparedStatement ps = connect.getPreparedStatement(query);

        try {
            ps.setString(1, generateAppointmentID());
            ps.setDate(2, appointmentDate);
            ps.setString(3, userID);
            ps.setString(4, doctorID);
            ps.setFloat(5, timeSlot);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connect.closePreparedStatement();
        }
    }
	 
	public String generateAppointmentID() {
	    int appointmentIndex = 0;
	    
	    System.out.println("Method generateAppointmentID ke panggil");

	    String query = "SELECT MAX(CAST(SUBSTRING(AppointmentID, 3) AS SIGNED)) AS maxIndex FROM appointment";
	    connect.rs = connect.execQuery(query);

	    try {
	        if (connect.rs.next()) {
	            int maxIndex = connect.rs.getInt("maxIndex");
	            appointmentIndex = maxIndex + 1;  // Increment the index based on the maxIndex
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    String indexStr = String.format("%03d", appointmentIndex);

	    return "AP" + indexStr;
	}
	
}
