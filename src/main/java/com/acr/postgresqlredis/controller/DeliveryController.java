package com.acr.postgresqlredis.controller;

import com.acr.postgresqlredis.dto.Delivery;
import com.acr.postgresqlredis.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("delivery")
public class DeliveryController {

    @Autowired
    public DeliveryService deliveryService;

    @GetMapping("/getDeliveries")
    public ResponseEntity<List<Delivery>> getAllDeliveries() {

        return ResponseEntity.ok(deliveryService.getAllDeliveries());

    }

    @GetMapping("/getDeliveries/{deliveryId}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable int deliveryId) {

        System.out.println("Request is in delivery controller");

        return ResponseEntity.ok(deliveryService.getDeliveryById(deliveryId));

    }

}
