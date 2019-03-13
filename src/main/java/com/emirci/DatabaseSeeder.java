package com.emirci;

import com.emirci.Model.VehicleUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    //private VehicleUsageService service = VehicleUsageService.getInstance();

    @Autowired
    VehicleUsageRepository repository;


    public DatabaseSeeder(VehicleUsageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        Random r = new Random(0);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                .withLocale(Locale.US);

        List<VehicleUsage> vehicleUsages = new ArrayList<>();

        for (int i = 0; i < 0; i++) {
            int daysOld = 0 - r.nextInt(365 * 15 + 365 * 60);

            VehicleUsage model = new VehicleUsage("Serdar EMIRCI", "kod tarafından basıldı", LocalDateTime.now(), LocalDateTime.now(), "347651", "347698","06 EZ 2323");

            vehicleUsages.add(model);

            repository.save(model);

        }
    }
}
