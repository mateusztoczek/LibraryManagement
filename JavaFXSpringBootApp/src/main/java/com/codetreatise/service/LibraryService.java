package com.codetreatise.service;

import java.util.List;

import com.codetreatise.bean.Library;
import com.codetreatise.generic.GenericService;

public interface LibraryService extends GenericService<Library>{
	List<Library> findByName(String name);
	List<Library> findByLocation(long idLocation);
	List<Library> findByOwner(Long idOwner);
	List<Library> findByTelephone(String telephone);
	List<Library> findByNumEmployees(int numEmployees);
}
