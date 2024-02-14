package com.acr.postgresqlredis.service;

import com.acr.postgresqlredis.dto.Delivery;
import com.acr.postgresqlredis.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {


    @Autowired
    DeliveryRepository deliveryRepository;


    @Cacheable(cacheNames = "deliveries")
    public List<Delivery> getAllDeliveries() {

        return deliveryRepository.findAll();

    }

    @Cacheable(value = "delivery", key = "#deliveryId")
    public Delivery getDeliveryById(int deliveryId) {

        System.out.println("Request is in delivery service");

        Optional<Delivery> deliveryById = deliveryRepository.findDeliveryById(deliveryId);

        return deliveryById.orElse(null);

    }


}
