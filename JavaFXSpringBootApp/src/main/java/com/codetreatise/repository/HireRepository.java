package com.codetreatise.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codetreatise.bean.Hire;


@Repository
@Qualifier("Hire")
public interface HireRepository extends JpaRepository<Hire, Long> {
	List<Hire> findByCopy_id(long idCopy);
	List<Hire> findByDateHire(LocalDate dateHire);
	List<Hire> findByDateBack(LocalDate dateBack);
	List<Hire> findByReader_id(long idReader);
	List<Hire> findByCopy_book_idBook(long idBook);
	List<Hire> findByPenalty(int penalty);
	
	List<Hire> findByCopy_idAndReader_id(long idCopy, long idReader);
	
}
