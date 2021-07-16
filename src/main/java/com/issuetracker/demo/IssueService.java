package com.issuetracker.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
	
	@Autowired
	private IssuesRepository issueRepo;
	
	public List<Issues> listAll(){
		return issueRepo.findAll();
	}
	
	public Issues get(Long id) {
		return issueRepo.findById(id).get();
	}
	
	public void save(Issues issue) {
		issueRepo.save(issue);
	}
}
