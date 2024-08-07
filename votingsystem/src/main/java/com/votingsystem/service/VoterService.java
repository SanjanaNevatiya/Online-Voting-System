package com.votingsystem.service;

import java.util.List;
import java.util.Optional;

import com.votingsystem.entity.Voter;

public interface VoterService {
	
	public Voter saveVoter(Voter voter);
	public List<Voter>voterlist();
	public void deleteById(int id);
	public Optional<Voter> findById(int id);
	public Voter updateVoter(Voter voter,int id);
	public boolean existsById(int voterId);
	

}
