package com.acr.postgresqlredis;

import com.acr.postgresqlredis.dto.Checkpoint;
import com.acr.postgresqlredis.dto.Delivery;
import com.acr.postgresqlredis.repository.CheckpointRepository;
import com.acr.postgresqlredis.repository.DeliveryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.rmi.server.ServerNotActiveException;
import java.util.*;

@SpringBootApplication
@EnableCaching
public class PostgreSqlRedisApplication implements CommandLineRunner {

    @Autowired
    public DeliveryRepository deliveryRepository;

    @Autowired
    public CheckpointRepository checkpointRepository;

    public static void main(String[] args) {

        SpringApplication.run(PostgreSqlRedisApplication.class, args);

    }

    @Override
    public void run(String... args) {

        Delivery delivery = Delivery.builder()
                .consignee("John Doe")
                .consignor("Jack Doe")
                .consigneeAddress("Cupertino")
                .consignorAddress("NYC")
                .number(UUID.randomUUID().toString())
                .build();

        Checkpoint created = Checkpoint.builder()
                .time(new Date())
                .message("CREATED")
                .delivery(delivery)
                .build();

        Checkpoint stocked = Checkpoint.builder()
                .time(new Date())
                .message("STOCK")
                .delivery(delivery)
                .build();

        Checkpoint shipped = Checkpoint.builder()
                .time(new Date())
                .message("SHIPPED")
                .delivery(delivery)
                .build();

        Checkpoint delivered = Checkpoint.builder()
                .time(new Date())
                .message("DELIVERED")
                .delivery(delivery)
                .build();

        List<Checkpoint> listOfCheckpoints = List.of(created, stocked, shipped, delivered);

        delivery.setCheckpoints(listOfCheckpoints);

        Delivery savedDelivery = deliveryRepository.save(delivery);

        checkpointRepository.saveAll(listOfCheckpoints);

        Optional<Delivery> byId = deliveryRepository.findDeliveryById(savedDelivery.getId());

        byId.ifPresent(delivery1 -> {

            List<Checkpoint> checkpoints = delivery1.getCheckpoints();

            if (!checkpoints.isEmpty()) {

                checkpoints.forEach(checkpoint1 -> System.out.println(checkpoint1.getMessage()));

            } else {

                System.out.println("No checkpoints for delivery with ID " + savedDelivery.getId());

            }

        });

    }
}
