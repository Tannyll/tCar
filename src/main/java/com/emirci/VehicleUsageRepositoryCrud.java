package com.emirci;

import com.emirci.Model.VehicleUsage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public interface VehicleUsageRepositoryCrud extends CrudRepository<VehicleUsage, Long> {


}
