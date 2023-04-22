package com.codetreatise.service;

import java.util.List;

import com.codetreatise.bean.Reader;
import com.codetreatise.generic.GenericService;

public interface ReaderService extends GenericService<Reader>{
	List<Reader> findByStatus(int status);
	boolean existsById(long id);
}
