
package com.codetreatise.service;

import java.util.List;

import com.codetreatise.bean.Author;
import com.codetreatise.generic.GenericService;

public interface AuthorService extends GenericService<Author> {
	List<Author> findByName(String name);
	List<Author> findBySurname(String surname);
	
}
