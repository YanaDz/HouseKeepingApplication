package com.dziadkouskaya.housekeeping.repository;

import com.dziadkouskaya.housekeeping.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HouseRepo extends JpaRepository<House, Long>, JpaSpecificationExecutor<House> {
}
