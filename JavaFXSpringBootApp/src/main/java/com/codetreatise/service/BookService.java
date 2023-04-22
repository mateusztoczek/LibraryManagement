package com.codetreatise.service;

import java.util.List;

import com.codetreatise.bean.Book;
import com.codetreatise.generic.GenericService;

public interface BookService extends GenericService<Book> {

	List<Book> findByTitle(String title);
	List<Book> findByTitleContaining(String title);
	List<Book> findByYear(int year);
	List<Book> findByPrint(String print);
}
