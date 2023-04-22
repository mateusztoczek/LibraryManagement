package com.codetreatise.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.codetreatise.bean.Reader;

@Repository
@Qualifier("Reader")
public interface ReaderRepository extends JpaRepository<Reader, Long>{
	List<Reader> findByStatus(int status);
	boolean existsById(long id);

}
