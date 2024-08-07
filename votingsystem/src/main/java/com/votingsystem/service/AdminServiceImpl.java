package com.votingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votingsystem.entity.Admin;
import com.votingsystem.repository.Adminrepository;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	Adminrepository adminRepo;
	
	@Override
	public void saveAdmin(Admin admin) {
		// TODO Auto-generated method stub
		adminRepo.save(admin);
		
	}
	
	@Override
	public List<Admin> adminlist(){
		List<Admin> find = adminRepo.findAll();
		return find;
		
	}
	

}
