package com.codetreatise.service;

import java.time.LocalDate;
import java.util.List;
import com.codetreatise.bean.Hire;
import com.codetreatise.generic.GenericService;

public interface HireService extends GenericService<Hire>{
	List<Hire> findByCopy_id(long idCopy);
	List<Hire> findByDateHire(LocalDate dateHire);
	List<Hire> findByDateBack(LocalDate dateBack);
	List<Hire> findByReader_id(long idReader);

	List<Hire> findByCopy_book_idBook(long idBook);
	List<Hire> findByCopy_idAndReader_id(long Copy, long idReader);
	List<Hire> findByPenalty(int penalty);
}
