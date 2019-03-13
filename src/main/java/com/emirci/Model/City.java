package com.emirci.model;


public class City {


	private Integer cityId;
	

	private String name;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "City [name=" + name + "]";
	}

	public City(Integer cityId, String name) {
		super();
		this.cityId = cityId;
		this.name = name;
	}

	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
