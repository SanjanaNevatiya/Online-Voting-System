package com.votingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votingsystem.entity.Voter;
import com.votingsystem.repository.VoterRepository;

@Service
public class VoterServiceImpl implements VoterService {

	@Autowired
	VoterRepository voterRepo;
	
	@Override
	public Voter saveVoter(Voter voter) {
		// TODO Auto-generated method stub
		return voterRepo.save(voter);
	}

	@Override
	public List<Voter> voterlist() {
		// TODO Auto-generated method stub
		List<Voter> find = voterRepo.findAll();
		return find;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		voterRepo.deleteById(id);
		
	}

	@Override
	public Optional<Voter> findById(int voterId) {
	    return voterRepo.findById(voterId);
	}


	@Override
	public Voter updateVoter(Voter voter,int id) {
		Optional<Voter> voterdatab = voterRepo.findById(id);
		if(voterdatab.isPresent()) {
			Voter newVoter=voterdatab.get();
			newVoter.setId(voter.getId());
			newVoter.setVoterName(voter.getVoterName());
			newVoter.setDOB(voter.getDOB());
			newVoter.setGender(voter.getGender());
			newVoter.setUsername(voter.getUsername());
			newVoter.setPassword(voter.getPassword());
			newVoter.setAddress(voter.getAddress());
			voterRepo.save(newVoter);
			return newVoter;
		}
		return null;
	}
	
	public boolean existsById(int voterId) {
	    return voterRepo.existsById(voterId);
	}
	
	

}
