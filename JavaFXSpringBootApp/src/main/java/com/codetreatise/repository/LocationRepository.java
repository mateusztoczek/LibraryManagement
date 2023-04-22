package com.codetreatise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codetreatise.bean.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	List<Location> findByCity(String city);
	List<Location> findByStreet(String street);
	List<Location> findByPostCode(String postCode);
}
