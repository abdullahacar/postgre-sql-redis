package com.acr.postgresqlredis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "checkpoints")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Checkpoint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(name = "message")
    String message;

    @Column(name = "checkpoint_time")
    @Temporal(value = TemporalType.DATE)
    Date time;

    @JsonIgnore // Infinite recursion (StackOverflowError) occurs when this is absent !!!
    @ManyToOne
    @JoinColumn(name = "delivery_id", referencedColumnName = "id", nullable = false)
    Delivery delivery;

}
