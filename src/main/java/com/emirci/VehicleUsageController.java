package com.emirci;

import com.emirci.Model.VehicleUsage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping(value = "VehicleUsageRest")
public class VehicleUsageController {

    @Inject
    VehicleUsageService service;
    private VehicleUsageRepositoryCrud vehicleUsageDao;

    @RequestMapping("/create")
    @ResponseBody
    public String create(String driverName, LocalDateTime outDate) {
        VehicleUsage VehicleUsage = null;
        try {
            VehicleUsage = new VehicleUsage(driverName, "dsfssdfdsfs dfsd", outDate, LocalDateTime.now(), "234234", "234234234","06 EZ 2323");
            vehicleUsageDao.save(VehicleUsage);
        } catch (Exception ex) {
            return "Error creating the VehicleUsage: " + ex.toString();
        }
        return "VehicleUsage succesfully created! (id = " + VehicleUsage.getId() + ")";
    }

    /**
     * /delete  --> Delete the VehicleUsage having the passed id.
     *
     * @param id The id of the VehicleUsage to delete
     * @return A string describing if the VehicleUsage is succesfully deleted or not.
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        try {

        } catch (Exception ex) {
            return "Error deleting the VehicleUsage: " + ex.toString();
        }
        return "VehicleUsage succesfully deleted!";
    }

    /**
     * /get-by-email  --> Return the id for the VehicleUsage having the passed email.
     *
     * @param driveName The email to search in the database.
     * @return The VehicleUsage id or a message error if the VehicleUsage is not found.
     */
    @RequestMapping("/get-by-driveName")
    @ResponseBody
    public String getByDriveName(String driveName) {
        String VehicleUsageId;
        try {
            VehicleUsage VehicleUsage = null;// vehicleUsageDao.findByDriveName(driveName);
            VehicleUsageId = String.valueOf(VehicleUsage.getId());
        } catch (Exception ex) {
            return "VehicleUsage not found";
        }
        return "The VehicleUsage id is: " + VehicleUsageId;
    }

    /**
     * /update  --> Update the email and the name for the VehicleUsage in the database
     * having the passed id.
     *
     * @param id         The id for the VehicleUsage to update.
     * @param driverName The new email.
     * @param outDate    The new name.
     * @return A string describing if the VehicleUsage is succesfully updated or not.
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateVehicleUsage(long id, String driverName, LocalDateTime outDate) {
        try {
            VehicleUsage VehicleUsage = vehicleUsageDao.findOne(id);
            VehicleUsage.setDriverName(driverName);
            VehicleUsage.setOutDate(outDate);
            vehicleUsageDao.save(VehicleUsage);
        } catch (Exception ex) {
            return "Error updating the VehicleUsage: " + ex.toString();
        }
        return "VehicleUsage succesfully updated!";
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<VehicleUsage> getAll() {
        return (List<VehicleUsage>) vehicleUsageDao.findAll();
    }

}
