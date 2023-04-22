package com.codetreatise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreatise.bean.Location;
import com.codetreatise.repository.LocationRepository;
import com.codetreatise.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
	@Autowired
	private LocationRepository locationRepository;

	@Override
	public Location save(Location entity) {
		// TODO Auto-generated method stub
		return locationRepository.save(entity);
	}

	@Override
	public Location update(Location entity) {
		return locationRepository.save(entity);
	}

	@Override
	public void delete(Location entity) {
		locationRepository.delete(entity);
		
	}

	@Override
	public void delete(Long id) {
		locationRepository.delete(id);
		
	}

	@Override
	public void deleteInBatch(List<Location> location) {
		locationRepository.deleteInBatch(location);
		
	}

	@Override
	public Location find(Long id) {
		return locationRepository.findOne(id);
	}

	@Override
	public List<Location> findAll() {
		return locationRepository.findAll();
	}

	@Override
	public List<Location> findByCity(String city) {
		return locationRepository.findByCity(city);
	}

	@Override
	public List<Location> findByStreet(String street) {
		return locationRepository.findByStreet(street);
	}

	@Override
	public List<Location> findByPostCode(String postCode) {
		return locationRepository.findByPostCode(postCode);
	}

}
