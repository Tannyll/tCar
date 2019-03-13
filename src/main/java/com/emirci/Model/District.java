package com.emirci.model;


public class District {

	private Integer districtId;
	
	
	private String name;
	
	private Integer cityId;

	
	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public District(Integer districtId, String name, Integer cityId) {
		super();
		this.districtId = districtId;
		this.name = name;
		this.cityId = cityId;
	}

	public District() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "District [districtId=" + districtId + ", name=" + name + ", cityId=" + cityId + "]";
	}
	
	
}
