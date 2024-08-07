package com.votingsystem.service;

import java.util.List;

import com.votingsystem.entity.Admin;

public interface AdminService {
	
	public void saveAdmin(Admin admin);
	
	public List<Admin> adminlist () ;
	
}
