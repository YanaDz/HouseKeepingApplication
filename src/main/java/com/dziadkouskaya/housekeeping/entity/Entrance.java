package com.dziadkouskaya.housekeeping.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "entrance")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Entrance extends AuditableEntity<Long> {
    @Column(name = "entrance_name")
    private String entranceName;

    @Column(name = "floor_number")
    private int floorsNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

    @Builder.Default
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "entrance", orphanRemoval = true)
    private Set<Floor> floors = new HashSet<>();

}
