package com.emirci;

import com.emirci.Model.VehicleUsage;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

@SpringComponent
public class VehicleuseLayout extends VerticalLayout {
    private VehicleUsageService service = VehicleUsageService.getInstance();
        /*
        @Autowired
            VehicleUsageRepository repo;

            */

    private List<VehicleUsage> vehicleUsages;


    private void update() {
        setVehicleuse(service.findAll());
    }

    private void setVehicleuse(List<VehicleUsage> vehicleUsages) {
        this.vehicleUsages = vehicleUsages;

        removeAllComponents();

        //vehicleUsages.forEach(vehicleUsage -> addComponent(new VehicleuseItemLayout(vehicleUsage)));
    }

    public void add(VehicleUsage vehicleUsage) {
        try {
            service.save(vehicleUsage);
            //repo.save(vehicleUsage);
            update();
        } catch (Exception e) {

        }

    }

    public void deleteCompleted() {

        //repo.delete();
        update();
    }
}
