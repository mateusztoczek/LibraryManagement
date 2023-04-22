package com.codetreatise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreatise.bean.Book;
import com.codetreatise.repository.BookRepository;
import com.codetreatise.service.BookService;


@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book save(Book entity) {
		// TODO Auto-generated method stub
		return bookRepository.save(entity);
	}

	@Override
	public Book update(Book entity) {
		// TODO Auto-generated method stub
		return bookRepository.save(entity);
	}

	@Override
	public void delete(Book entity) {
		bookRepository.delete(entity);	
	}

	@Override
	public void delete(Long id) {
		bookRepository.delete(id);
		
	}

	@Override
	public void deleteInBatch(List<Book> book) {
		bookRepository.deleteInBatch(book);
		
	}

	@Override
	public Book find(Long id) {
		return bookRepository.findOne(id);
	}

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> findByTitle(String title) {
		return bookRepository.findByTitle( title);
	}
	
	@Override
	public List<Book> findByTitleContaining(String title) {
		return bookRepository.findByTitleContaining( title);
	}
	

	@Override
	public List<Book> findByYear(int year) {
		return bookRepository.findByYear(year);
	}

	@Override
	public List<Book> findByPrint(String print) {
		return bookRepository.findByPrint(print);
	}

}
