package com.acr.postgresqlredis.repository;

import com.acr.postgresqlredis.dto.Checkpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckpointRepository extends JpaRepository<Checkpoint, Integer> {
}
