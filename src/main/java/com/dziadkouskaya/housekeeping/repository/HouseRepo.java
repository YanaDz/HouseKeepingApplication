package com.dziadkouskaya.housekeeping.repository;

import com.dziadkouskaya.housekeeping.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface HouseRepo extends CrudRepository<House, Long>, JpaSpecificationExecutor<House> {
}
