package com.emirci.model;

import org.springframework.data.annotation.Id;

public class Person {

	@Id
	private int personId;
	
	private Integer companyId;
	private Integer cityId;
	private String email;
	private String addressId;
	private Integer bloodGroupId;
	private String fullName;
	private int genderId;
	
	/**
	 * @return the personId
	 */
	public int getPersonId() {
		return personId;
	}
	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	/**
	 * @return the companyId
	 */
	public Integer getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the cityId
	 */
	public Integer getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the addressId
	 */
	public String getAddressId() {
		return addressId;
	}
	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	/**
	 * @return the bloodGroupId
	 */
	public Integer getBloodGroupId() {
		return bloodGroupId;
	}
	/**
	 * @param bloodGroupId the bloodGroupId to set
	 */
	public void setBloodGroupId(Integer bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the genderId
	 */
	public int getGenderId() {
		return genderId;
	}
	/**
	 * @param genderId the genderId to set
	 */
	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}
	
	/**
	 * 
	 */
	public Person() {
	}
	
	/**
	 * @param email
	 * @param fullName
	 */
	public Person(String email, String fullName) {
		this.email = email;
		this.fullName = fullName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [email=" + email + ", fullName=" + fullName + "]";
	}
	
	 
	
			
}