package com.issuetracker.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {
	
	@Autowired
	private EquipmentRepository equipRepo;
	
	public List<Equipment> listAll(){
		return equipRepo.findAll();
	}
	
	public Equipment get(Long id) {
		return equipRepo.findById(id).get();
	}
	
	public void save(Equipment equip) {
		equipRepo.save(equip);
	}
}
