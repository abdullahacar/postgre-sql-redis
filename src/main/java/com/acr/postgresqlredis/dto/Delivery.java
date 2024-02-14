package com.acr.postgresqlredis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "deliveries")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Delivery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String number;
    String consignor;
    String consignee;
    String consignorAddress;
    String consigneeAddress;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Checkpoint> checkpoints;


}
