package com.codetreatise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreatise.bean.Copy;
import com.codetreatise.repository.CopyRepository;
import com.codetreatise.service.CopyService;

@Service
public class CopyServiceImpl implements CopyService {
	
	@Autowired
	private CopyRepository copyRepository;
	
	@Override
	public Copy save(Copy entity) {
		return copyRepository.save(entity);
	}

	@Override
	public Copy update(Copy entity) {
		return copyRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		copyRepository.delete(id);
	}

	@Override
	public Copy find(Long id) {
		return copyRepository.findOne(id);
	}

	@Override
	public List<Copy> findAll() {
		return copyRepository.findAll();
	}

	@Override
	public void delete(Copy entity) {
		copyRepository.delete(entity);
		
	}

	@Override
	public void deleteInBatch(List<Copy> copy) {
		copyRepository.deleteInBatch(copy);
		
	}

	@Override
	public List<Copy> findByBook_idBook(long idBook) {
		return copyRepository.findByBook_idBook(idBook);
	}

	@Override
	public List<Copy> findByLibrary_idLibrary(long idLibrary) {
		return copyRepository.findByLibrary_idLibrary(idLibrary);
	}

	@Override
	public List<Copy> findByStatus(String status) {
		return copyRepository.findByStatus(status);
	}

	@Override
	public List<Copy> findByBook_idBookAndLibrary_idLibrary(long idBook,  long idLibrary) {
		// TODO Auto-generated method stub
		return copyRepository.findByBook_idBookAndLibrary_idLibrary(idBook,  idLibrary);
	}

	@Override
	public List<Copy> findByBook_idBookAndStatus(long idBook, String status) {
		// TODO Auto-generated method stub
		return copyRepository.findByBook_idBookAndStatus(idBook, status);
	}

	@Override
	public List<Copy> findByBook_idBookAndStatusAndLibrary_idLibrary(long idBook, String status, long idLibrary) {
		// TODO Auto-generated method stub
		return copyRepository.findByBook_idBookAndStatusAndLibrary_idLibrary(idBook, status, idLibrary);
	}

	@Override
	public List<Copy> findByBook_idBookAndLibrary_Location_city(long idBook, String city) {
		// TODO Auto-generated method stub
		return copyRepository.findByBook_idBookAndLibrary_Location_city(idBook, city);
	}

	@Override
	public List<Copy> findByBook_idBookAndLibrary_Location_street(long idBook, String street) {
		// TODO Auto-generated method stub
		return copyRepository.findByBook_idBookAndLibrary_Location_street(idBook, street);
	}

	@Override
	public List<Copy> findByBook_idBookAndLibrary_Location_postCode(long idBook, String postCode) {
		// TODO Auto-generated method stub
		return copyRepository.findByBook_idBookAndLibrary_Location_postCode(idBook, postCode);
	}
	
}
