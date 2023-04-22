package com.codetreatise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreatise.bean.Reader;
import com.codetreatise.repository.ReaderRepository;
import com.codetreatise.service.ReaderService;

@Service
public class ReaderServiceImpl implements ReaderService{
	@Autowired
	private ReaderRepository readerRepository;

	@Override
	public Reader save(Reader entity) {
		// TODO Auto-generated method stub
		return readerRepository.save(entity);
	}

	@Override
	public Reader update(Reader entity) {
		// TODO Auto-generated method stub
		return readerRepository.save(entity);
	}

	@Override
	public void delete(Reader entity) {
		readerRepository.delete(entity);		
	}

	@Override
	public void delete(Long id) {
		readerRepository.delete(id);		
	}

	@Override
	public void deleteInBatch(List<Reader> reader) {
		readerRepository.deleteInBatch(reader);		
	}

	@Override
	public Reader find(Long id) {
		return readerRepository.findOne(id);
	}

	@Override
	public List<Reader> findAll() {
		return readerRepository.findAll();
	}

	@Override
	public List<Reader> findByStatus(int status) {
		return readerRepository.findByStatus(status);
	}

	@Override
	public boolean existsById(long id) {
		// TODO Auto-generated method stub
		return readerRepository.existsById(id);
	}
	

}
