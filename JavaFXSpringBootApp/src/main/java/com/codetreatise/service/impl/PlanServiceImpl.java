package com.codetreatise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreatise.bean.Plan;
import com.codetreatise.repository.PlanRepository;
import com.codetreatise.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService{

	@Autowired
	private PlanRepository planRepository;
	
	@Override
	public Plan save(Plan entity) {
		// TODO Auto-generated method stub
		return planRepository.save(entity);
	}

	@Override
	public Plan update(Plan entity) {
		// TODO Auto-generated method stub
		return planRepository.save(entity);
	}

	@Override
	public void delete(Plan entity) {
		planRepository.delete(entity);
		
	}

	@Override
	public void delete(Long id) {
		planRepository.delete(id);		
	}

	@Override
	public void deleteInBatch(List<Plan> plans) {
		planRepository.deleteInBatch(plans);		
	}

	@Override
	public Plan find(Long id) {
		return planRepository.findOne(id);
	}

	@Override
	public List<Plan> findAll() {
		return planRepository.findAll();
	}

	@Override
	public List<Plan> findByUser_library_idLibrary(long id) {
		return planRepository.findByUser_library_idLibrary(id);
	}

	@Override
	public List<Plan> findByUser_id(long id) {
		return planRepository.findByUser_id(id);
	}

}
