package com.dziadkouskaya.housekeeping.entity;

import com.dziadkouskaya.housekeeping.entity.enumerations.OccupationRole;
import com.dziadkouskaya.housekeeping.entity.enumerations.OccupationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "occupant")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Occupant extends AuditableEntity<Long> {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "role")
    private OccupationRole role;
    @Column(name = "status")
    private OccupationStatus status;

    @ManyToMany
    @JoinTable(
        name = "flatMates",
        joinColumns = @JoinColumn(name = "occupant_id"),
        inverseJoinColumns = @JoinColumn(name = "flat_id")
    )
    private Set<Flat> flats = new HashSet<>();
}
