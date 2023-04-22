package com.codetreatise.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codetreatise.bean.Plan;
@Repository
@Qualifier("Plan")
public interface PlanRepository extends JpaRepository<Plan, Long> {
	List<Plan> findByUser_library_idLibrary(long idLibrary);
	List<Plan> findByUser_id(long id);

}
