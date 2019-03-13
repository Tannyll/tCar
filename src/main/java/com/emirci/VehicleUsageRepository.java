package com.emirci;

import com.emirci.Model.VehicleUsage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Repository
@Transactional
public interface VehicleUsageRepository extends JpaRepository<VehicleUsage, Long> {


    @Override
    List<VehicleUsage> findAll();

    @Override
    VehicleUsage getOne(Long aLong);


    @Query("SELECT  venicleEntKm FROM VehicleUsage v  WHERE v.licensePlate=(:liSTR)  ORDER BY id desc")
    List<VehicleUsage> findVehicleUsageByLicensePlateAndOrderByIdDesc(@Param("liSTR") String liSTR, Pageable pag);




}
