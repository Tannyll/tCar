package com.emirci.model;


public class BloodGroup {
	private static final long serialVersionUID = 1L;

	private int bloodGroupId;
	
	private String bloodName;

	public int getBloodGroupId() {
		return bloodGroupId;
	}

	public void setBloodGroupId(int bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}

	public String getBloodName() {
		return bloodName;
	}

	public void setBloodName(String bloodName) {
		this.bloodName = bloodName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BloodGroup(int bloodGroupId, String bloodName) {
		super();
		this.bloodGroupId = bloodGroupId;
		this.bloodName = bloodName;
	}

	public BloodGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BloodGroup [bloodGroupId=" + bloodGroupId + ", bloodName=" + bloodName + "]";
	}
	
	
}
