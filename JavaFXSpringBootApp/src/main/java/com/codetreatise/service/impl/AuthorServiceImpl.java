package com.codetreatise.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreatise.bean.Author;
import com.codetreatise.repository.AuthorRepository;
import com.codetreatise.service.AuthorService;

@Service
public class AuthorServiceImpl  implements AuthorService{
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Author save(Author entity) {
		// TODO Auto-generated method stub
		return authorRepository.save(entity);
	}

	@Override
	public Author update(Author entity) {
		// TODO Auto-generated method stub
		return authorRepository.save(entity);
	}

	@Override
	public void delete(Author entity) {
		authorRepository.delete(entity);		
	}

	@Override
	public void delete(Long id) {
		authorRepository.delete(id);		
	}

	@Override
	public void deleteInBatch(List<Author> author) {
		authorRepository.deleteInBatch(author);
		
	}

	@Override
	public Author find(Long id) {
		return authorRepository.findOne(id);
	}

	@Override
	public List<Author> findAll() {
		return authorRepository.findAll();
	}

	@Override
	public List<Author> findByName(String name) {
		return authorRepository.findByName(name);
	}

	@Override
	public List<Author> findBySurname(String surname) {
		return authorRepository.findBySurname(surname);
	}

}
