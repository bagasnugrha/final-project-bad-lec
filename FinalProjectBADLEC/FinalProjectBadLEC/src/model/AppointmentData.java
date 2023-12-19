package model;

import java.sql.Date;

public class AppointmentData {
	
	String appointmentID;
	Date appointmentDate;
	Float timeSlot;
	String doctorID;
	String doctorName;
	String userID;
	String userName;

	
	public AppointmentData(String appointmentID, String doctorName, Date appointmentDate, Float timeSlot) {
		this.appointmentID = appointmentID;
		this.doctorName = doctorName;
		this.appointmentDate = appointmentDate;
		this.timeSlot = timeSlot;
	}

//	public AppointmentData(String appointmentID, Date appointmentDate, Float timeSlot, String userID) {
//		this.appointmentID = appointmentID;
//		this.appointmentDate = appointmentDate;
//		this.timeSlot = timeSlot;
//		this.userID = userID;
//	}


	public String getAppointmentID() {
		return appointmentID;
	}


	public void setAppointmentID(String appointmentID) {
		this.appointmentID = appointmentID;
	}


	public Date getAppointmentDate() {
		return appointmentDate;
	}


	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}


	public Float getTimeSlot() {
		return timeSlot;
	}


	public void setTimeSlot(Float timeSlot) {
		this.timeSlot = timeSlot;
	}


	public String getDoctorID() {
		return doctorID;
	}


	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}


	public String getDoctorName() {
		return doctorName;
	}


	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
