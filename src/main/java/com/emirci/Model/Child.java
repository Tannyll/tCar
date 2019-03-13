package com.emirci.model;


import java.sql.Date;

public class Child {
	
	private int childId;
	
	private Integer personId;
	private String childName;
	private Date dateBirth;
	private Integer genderId;
	private int bloodGroupId;
	
	public int getBloodGroupId() {
		return bloodGroupId;
	}
	public void setBloodGroupId(int bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}
	public int getChildId() {
		return childId;
	}
	public void setChildId(int childId) {
		this.childId = childId;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public Date getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	public Integer getGenderId() {
		return genderId;
	}
	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	
	public Child(int childId, Integer personId, String childName) {
		super();
		this.childId = childId;
		this.personId = personId;
		this.childName = childName;
	}
	
	public Child() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Child [childId=" + childId + ", personId=" + personId + ", childName=" + childName + ", dateBirth="
				+ dateBirth + ", genderId=" + genderId + ", bloodGroupId=" + bloodGroupId + "]";
	}
	
	
}
