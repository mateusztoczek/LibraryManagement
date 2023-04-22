package com.codetreatise.repository;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.codetreatise.bean.Author;
import java.util.List;

@Repository
@Qualifier("Author")
public interface AuthorRepository extends JpaRepository<Author, Long> {
	List<Author> findByName(String name);
	List<Author> findBySurname(String surname);


}