package com.codetreatise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codetreatise.bean.Library;
@Repository
public interface LibraryRepository  extends JpaRepository<Library, Long>{
	List<Library> findByName(String name);
	List<Library> findByLocation(long idLocation);
	List<Library> findByOwner(long idOwner);
	List<Library> findByTelephone(String telephone);
	List<Library> findByNumEmployees(int num_employees);

}
