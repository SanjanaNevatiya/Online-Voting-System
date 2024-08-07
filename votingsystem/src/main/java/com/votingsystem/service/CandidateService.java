package com.votingsystem.service;

import java.util.List;
import java.util.Optional;

import com.votingsystem.entity.Candidate;

public interface CandidateService {

	public Candidate saveCandidate(Candidate candidate);
	public List<Candidate>candidatelist();
	public void deleteById(int id);
	public Optional<Candidate> findById(int id);
	public Candidate updateCandidate(Candidate candidate,int id);
	public boolean existsById(int candidateId);
}
