package com.dziadkouskaya.housekeeping.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "house")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class House extends AuditableEntity<Long> {

    @Column(name = "house_name")
    private String name;

    @Column(name = "house_address")
    private String address;

    @Column(name = "entrance_number")
    private int entranceNumber;

    @Builder.Default
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "house", orphanRemoval = true)
    private Set<Entrance> entrance = new HashSet<>();

}
