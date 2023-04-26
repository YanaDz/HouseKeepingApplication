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
@Table(name = "floor")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Floor extends AuditableEntity<Long>{
    @Column(name = "floor_number")
    private String floorNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrance_id")
    private Entrance entrance;

    @Builder.Default
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "floor", orphanRemoval = true)
    private Set<Flat> flats = new HashSet<>();

}
