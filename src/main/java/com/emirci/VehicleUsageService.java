package com.emirci;

import com.emirci.Model.VehicleUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class VehicleUsageService {

    private static final Logger LOGGER = Logger.getLogger(VehicleUsageService.class.getName());
    private static VehicleUsageService instance;
    private final HashMap<Long, VehicleUsage> longVehicleUsageHashMap = new HashMap<>();
    private long nextId = 0;

/*    @Autowired
    private JdbcTemplate jdbcTemplate;*/

    @Autowired
    private VehicleUsageRepository repository;

    private VehicleUsageService() {


    }

    public static VehicleUsageService getInstance() {
        if (instance == null) {
            instance = new VehicleUsageService();
            //instance.ensureTestData(15);
        }
        return instance;
    }

    public VehicleUsage getFirst() {
        return repository.findOne((long) 2);
    }

    public VehicleUsage getById(Long id) {
        return repository.findOne(id);
    }

    public List<VehicleUsage> findAll() {
        return repository.findAll();
    }

    public synchronized List<VehicleUsage> findAllOld() {
        return findAll(null);
    }

    public synchronized List<VehicleUsage> findAll(String stringFilter) {
        ArrayList<VehicleUsage> arrayList = new ArrayList<>();
        for (VehicleUsage vehicleUsage : longVehicleUsageHashMap.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || vehicleUsage.toString().toLowerCase().contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(vehicleUsage.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(VehicleUsageService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<VehicleUsage>() {

            @Override
            public int compare(VehicleUsage o1, VehicleUsage o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return arrayList;
    }

    public synchronized List<VehicleUsage> findAll(String stringFilter, int start, int maxresults) {
        ArrayList<VehicleUsage> arrayList = new ArrayList<>();

        return arrayList.subList(start, 5);
    }

    public synchronized long count() {
        return longVehicleUsageHashMap.size();
    }

    //@Secured("ROLE_ADMIN")
    public synchronized void deleteOld(VehicleUsage value) {
        longVehicleUsageHashMap.remove(value.getId());
    }

    public void delete(VehicleUsage entity) {
        // Hibernate cannot remove detached, reattach...
        repository.delete(repository.findOne(entity.getId()));
    }

    public synchronized void saveOld(VehicleUsage entry) {
        if (entry == null) {
            LOGGER.log(Level.SEVERE,
                    "Person is null. Are you sure you have connected your form to the application as described in tutorial chapter 7?");
            return;
        }

        if (entry.getId() == null) {
            entry.setId(nextId++);

        }

        try {
            entry = (VehicleUsage) entry.clone();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        longVehicleUsageHashMap.put(entry.getId(), entry);
    }

    public VehicleUsage save(VehicleUsage entity) {
        return repository.saveAndFlush(entity);
    }

    public void ensureTestData(int item) {
        if (findAll().isEmpty()) {

            final String[] names = new String[]
                    {
                            "Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
                            "Koen Johansen", "Alejandro Macdonald", "Angel Karlsson"
                    };

            Random r = new Random(0);

            for (int i = 0; i < item; i++) {
                for (String name : names) {
                    Calendar cal = Calendar.getInstance();
                    int daysOld = 0 - r.nextInt(365 * 15 + 365 * 60);
                    cal.add(Calendar.DAY_OF_MONTH, daysOld);

                    String[] split = name.split(" ");
                    VehicleUsage c = new VehicleUsage();
                    c.setDriverName(split[1]);
                    c.setEntDate(LocalDateTime.now());
                    c.setOutDate(LocalDateTime.now());
                    c.setVenicleEntKm("2342194");
                    c.setVenicleOutKm("2342134");
                    c.setComment(split.toString());

                    save(c);
                }
            }


        }
    }
}
