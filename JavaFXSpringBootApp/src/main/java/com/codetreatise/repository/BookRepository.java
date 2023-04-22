package com.codetreatise.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codetreatise.bean.Book;

@Repository
@Qualifier("Book")
public interface BookRepository extends JpaRepository<Book, Long> {
	
	List<Book> findByTitleContaining(String title);
	List<Book> findByTitle(String title);
	List<Book> findByYear(int year);
	List<Book> findByPrint(String print);
}
