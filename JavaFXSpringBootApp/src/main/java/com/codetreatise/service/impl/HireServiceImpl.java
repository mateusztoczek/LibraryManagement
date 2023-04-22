package com.codetreatise.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreatise.bean.Hire;
import com.codetreatise.repository.HireRepository;
import com.codetreatise.service.HireService;

@Service
public class HireServiceImpl implements HireService{
	@Autowired
	private HireRepository hireRepository;

	@Override
	public Hire save(Hire entity) {
		return hireRepository.save(entity);
	}

	@Override
	public Hire update(Hire entity) {
		return  hireRepository.save(entity);
	}

	@Override
	public void delete(Hire entity) {
		hireRepository.delete(entity);		
	}

	@Override
	public void delete(Long id) {
		hireRepository.delete(id);		
	}

	@Override
	public void deleteInBatch(List<Hire> hire) {
		hireRepository.deleteInBatch(hire);
	}

	@Override
	public Hire find(Long id) {
		return hireRepository.findOne(id);
	}

	@Override
	public List<Hire> findAll() {
		return hireRepository.findAll();
	}

	@Override
	public List<Hire> findByCopy_id(long idCopy) {
		return hireRepository.findByCopy_id(idCopy);
	}

	@Override
	public List<Hire> findByDateHire(LocalDate dateHire) {
		return hireRepository.findByDateHire(dateHire);
	}

	@Override
	public List<Hire> findByDateBack(LocalDate dateBack) {
		return hireRepository.findByDateBack(dateBack);
	}

	@Override
	public List<Hire> findByReader_id(long idReader) {
		return hireRepository.findByReader_id(idReader);
	}

	@Override
	public List<Hire> findByPenalty(int penalty) {
		return hireRepository.findByPenalty(penalty);
	}

	@Override
	public List<Hire> findByCopy_book_idBook(long idBook) {
		return hireRepository.findByCopy_book_idBook(idBook);
	}

	@Override
	public List<Hire> findByCopy_idAndReader_id(long Copy, long idReader) {
		// TODO Auto-generated method stub
		return hireRepository.findByCopy_idAndReader_id(Copy, idReader);
	}

	

	
}
