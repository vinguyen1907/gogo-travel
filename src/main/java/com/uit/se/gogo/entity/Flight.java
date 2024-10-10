package com.uit.se.gogo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Flight extends BaseService {
    private String address;

    @ManyToOne
    private Location location;
}
