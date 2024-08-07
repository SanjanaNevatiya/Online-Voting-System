package com.votingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votingsystem.entity.Candidate;
import com.votingsystem.entity.Voter;
import com.votingsystem.repository.CandidateRepository;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	CandidateRepository candidateRepo;
	
	@Override
	public Candidate saveCandidate(Candidate candidate) {
		// TODO Auto-generated method stub
		return candidateRepo.save(candidate);
	}

	@Override
	public List<Candidate> candidatelist() {
		// TODO Auto-generated method stub
		List<Candidate> find = candidateRepo.findAll();
		return find;
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		candidateRepo.deleteById(id);
	}

	@Override
	public Optional<Candidate> findById(int id) {
		// TODO Auto-generated method stub
		return candidateRepo.findById(id);
	}

	@Override
	public Candidate updateCandidate(Candidate candidate, int id) {
		// TODO Auto-generated method stub
		Optional<Candidate> candidatedatab = candidateRepo.findById(id);
		if(candidatedatab.isPresent()) {
			Candidate newCandidate=candidatedatab.get();
			newCandidate.setId(candidate.getId());
			newCandidate.setName(candidate.getName());
			newCandidate.setDOB(candidate.getDOB());
			newCandidate.setGender(candidate.getGender());
			newCandidate.setParty(candidate.getParty());
			newCandidate.setPosition(candidate.getPosition());
			newCandidate.setExpierence(candidate.getExpierence());
			newCandidate.setAddress(candidate.getAddress());
			newCandidate.setContact(candidate.getContact());
			newCandidate.setUsername(candidate.getUsername());
			newCandidate.setPassword(candidate.getPassword());
			candidateRepo.save(newCandidate);
			return newCandidate;
		}
		return null;
	}

	@Override
	public boolean existsById(int candidateId) {
		// TODO Auto-generated method stub
		return candidateRepo.existsById(candidateId);
	}

}
