package com.codetreatise.service;

import java.util.List;

import com.codetreatise.bean.Location;
import com.codetreatise.generic.GenericService;

public interface LocationService extends GenericService<Location> {

	List<Location> findByCity(String city);
	List<Location> findByStreet(String street);
	List<Location> findByPostCode(String postCode);
}
