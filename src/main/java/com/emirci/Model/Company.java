package com.emirci.model;

import java.io.Serializable;

public class Company implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer companyId;


	private String companyName;


	private String companyTitle;
	private Integer cityId;

	private Integer districtId;

	private String address;

	private String phone;
	private String fax;
	private String postalCode;
	private boolean status;

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyTitle() {
		return companyTitle;
	}

	public void setCompanyTitle(String companyTitle) {
		this.companyTitle = companyTitle;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Company(Integer companyId, String companyName) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
	}

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", companyTitle=" + companyTitle
				+ ", cityId=" + cityId + ", districtId=" + districtId + ", address=" + address + ", phone=" + phone
				+ ", fax=" + fax + ", postalCode=" + postalCode + ", status=" + status + "]";
	}

	
}