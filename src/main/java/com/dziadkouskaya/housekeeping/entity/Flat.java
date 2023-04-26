package com.dziadkouskaya.housekeeping.entity;

import com.dziadkouskaya.housekeeping.entity.enumerations.FlatType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "flat")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Flat extends AuditableEntity<Long>{
    @Column(name = "flat_number")
    private String flatNumber;

    @Column(name = "flat_type")
    private FlatType flatType;

    @Column(name = "full_area")
    private Double fullApartmentArea;

    @Column(name = "register_area")
    private Double registerApartmentArea;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @Builder.Default
    @ManyToMany(mappedBy = "flats")
    private List<Occupant> flatMates = new ArrayList<>();
}
