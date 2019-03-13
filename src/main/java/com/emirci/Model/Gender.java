package com.emirci.model;


public class Gender {
	
	private int genderId;
	
	private String genderName;

	public int getGenderId() {
		return genderId;
	}

	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}


	public Gender(int genderId, String genderName) {
		super();
		this.genderId = genderId;
		this.genderName = genderName;
	}

	public Gender() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Gender [genderId=" + genderId + ", genderName=" + genderName + "]";
	}
	
	
}
