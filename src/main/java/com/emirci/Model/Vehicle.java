package com.emirci.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by Serdar on 26/11/2016.
 */
@Entity
@Table(name = "Vehicle")
public class Vehicle extends AbstractEntity {


    private String licensePlate;
    private String owner;
    private String make;
    private String model;
    private String year;

    public Vehicle() {

    }

    public Vehicle(String licensePlate, String owner, String make) {
        this.licensePlate = licensePlate;
        this.owner = owner;
        this.make = make;
    }

    @ManyToMany(mappedBy = "vehicles")
    public Set<VehicleUsage> vehicleUsages() {
        return vehicleUsages();
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "Id=" + getId() +
                '}';
    }
}
