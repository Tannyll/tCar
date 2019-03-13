package com.emirci.Model;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Entity
@DynamicUpdate
@Table(name = "VehicleUsage")
public class VehicleUsage extends com.emirci.Model.AbstractEntity {

    @NotNull(message = "Sürücü adı zorunlu.")
    @Size(min = 3, max = 30, message = "First Name must be longer than 3 and less than 40 characters")
    private String driverName;

    private String licensePlate;

    private String destination;

    @Lob
    @Column(length = 1000)
    private String comment;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:MM")
    private LocalDateTime outDate;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:MM")
    private LocalDateTime entDate;

    private String venicleOutKm;
    private String venicleEntKm;




    public VehicleUsage() {

    }

    public VehicleUsage(String driverName) {
        this.driverName = driverName;
    }

    public VehicleUsage(String driverName, String comment, LocalDateTime outDate, LocalDateTime entDate, String venicleOutKm, String venicleEntKm, String licensePlate) {
        this.driverName = driverName;
        this.comment = comment;
        this.outDate = outDate;
        this.entDate = entDate;
        this.venicleOutKm = venicleOutKm;
        this.venicleEntKm = venicleEntKm;
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        return "VenicleUsage{" +
                "driverName='" + driverName + '\'' +
                ", outDate=" + outDate +
                '}';
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getOutDate() {
        return outDate;
    }

    public void setOutDate(LocalDateTime outDate) {
        this.outDate = outDate;
    }

    public LocalDateTime getEntDate() {
        return entDate;
    }

    public void setEntDate(LocalDateTime entDate) {
        this.entDate = entDate;
    }

    public String getVenicleOutKm() {
        return venicleOutKm;
    }

    public void setVenicleOutKm(String venicleOutKm) {
        this.venicleOutKm = venicleOutKm;
    }

    public String getVenicleEntKm() {
        return venicleEntKm;
    }

    public void setVenicleEntKm(String venicleEntKm) {
        this.venicleEntKm = venicleEntKm;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public VehicleUsage clone() throws CloneNotSupportedException {
        return (VehicleUsage)
                super.clone();
    }
}
