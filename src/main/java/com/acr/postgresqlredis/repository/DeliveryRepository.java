package com.acr.postgresqlredis.repository;

import com.acr.postgresqlredis.dto.Delivery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class DeliveryRepository {

    @PersistenceContext
    public EntityManager entityManager;

    public Optional<Delivery> findDeliveryById(int id) {

        System.out.println("Request is in delivery repository");

        return Optional.ofNullable(entityManager.find(Delivery.class, id));

    }

    @Transactional
    public Delivery save(Delivery delivery) {

        entityManager.persist(delivery);

        return delivery;

    }

    public List<Delivery> findAll() {

        String jpql = "SELECT c FROM Delivery c";

        TypedQuery<Delivery> query = entityManager.createQuery(jpql, Delivery.class);

        return query.getResultList();

    }


}
