package model;

import java.sql.Date;

public class User {
	String userID;
	String userName;
	String userGender;
	Date userDateOfBirth;
	String userPhoneNumber;
	
	public User(String userID, String userName, String userGender, Date userDateOfBirth, String userPhoneNumber) {
		this.userID = userID;
		this.userName = userName;
		this.userGender = userGender;
		this.userDateOfBirth = userDateOfBirth;
		this.userPhoneNumber = userPhoneNumber;
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

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public Date getUserDateOfBirth() {
		return userDateOfBirth;
	}

	public void setUserDateOfBirth(Date userDateOfBirth) {
		this.userDateOfBirth = userDateOfBirth;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	
	
}