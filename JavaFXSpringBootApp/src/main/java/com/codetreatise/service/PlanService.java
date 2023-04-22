package com.codetreatise.service;

import java.util.List;

import com.codetreatise.bean.Plan;
import com.codetreatise.generic.GenericService;

public interface PlanService extends GenericService<Plan> {
	List<Plan> findByUser_library_idLibrary(long idLibrary);
	List<Plan> findByUser_id(long id);


}
