package com.codetreatise.service;

import java.util.List;

import com.codetreatise.bean.Copy;
import com.codetreatise.generic.GenericService;

public interface CopyService extends GenericService<Copy> {

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
