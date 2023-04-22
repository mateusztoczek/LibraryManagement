package com.codetreatise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codetreatise.bean.Copy;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Long> {
	

	List<Copy> findByBook_idBook(long idBook);
	List<Copy>  findByBook_idBookAndLibrary_idLibrary(long idBook, long idLibrary);
	List<Copy>  findByBook_idBookAndStatus(long idBook, String status);
	List<Copy>  findByBook_idBookAndStatusAndLibrary_idLibrary(long idBook, String status, long idLibrary);
	List<Copy>  findByBook_idBookAndLibrary_Location_city(long idBook, String city);
	List<Copy>  findByBook_idBookAndLibrary_Location_street(long idBook, String street);
	List<Copy>  findByBook_idBookAndLibrary_Location_postCode(long idBook, String postCode);
	
	List<Copy> findByLibrary_idLibrary(long idLibrary);
	
	List<Copy> findByStatus(String status);
	
}
