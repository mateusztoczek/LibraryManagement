package com.codetreatise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreatise.bean.Library;
import com.codetreatise.repository.LibraryRepository;
import com.codetreatise.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService{
	@Autowired
	private LibraryRepository libraryRepository;

	@Override
	public Library save(Library entity) {
		// TODO Auto-generated method stub
		return libraryRepository.save(entity);
	}

	@Override
	public Library update(Library entity) {
		// TODO Auto-generated method stub
		return libraryRepository.save(entity);
	}

	@Override
	public void delete(Library entity) {
		libraryRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		libraryRepository.delete(id);
		
	}

	@Override
	public void deleteInBatch(List<Library> libraries) {
		libraryRepository.deleteInBatch(libraries);
		
	}

	@Override
	public Library find(Long id) {
		return libraryRepository.findOne(id);
	}

	@Override
	public List<Library> findAll() {
		return libraryRepository.findAll();
	}

	@Override
	public List<Library> findByLocation(long idLocation) {
		return libraryRepository.findByLocation(idLocation);
	}


	@Override
	public List<Library> findByTelephone(String telephone) {
		// TODO Auto-generated method stub
		return libraryRepository.findByTelephone(telephone);
	}

	@Override
	public List<Library> findByNumEmployees(int num_employees) {
		// TODO Auto-generated method stub
		return libraryRepository.findByNumEmployees(num_employees);
	}

	@Override
	public List<Library> findByName(String name) {
		// TODO Auto-generated method stub
		return libraryRepository.findByName(name);
	}

	@Override
	public List<Library> findByOwner(Long idOwner) {
		// TODO Auto-generated method stub
		return libraryRepository.findByOwner(idOwner);
	}


}
