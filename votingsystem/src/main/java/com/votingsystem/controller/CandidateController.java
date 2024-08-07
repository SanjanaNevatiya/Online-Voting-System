package com.votingsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.votingsystem.entity.Candidate;
import com.votingsystem.entity.Voter;
import com.votingsystem.repository.CandidateRepository;
import com.votingsystem.service.CandidateService;

import jakarta.validation.Valid;

@Controller
public class CandidateController {
	
	@Autowired
	CandidateRepository candidateRepo;
	
	@Autowired
	CandidateService candidateService;
	
	@GetMapping("/saveCandidate")
	public String saveCandidate(Model model) {
		model.addAttribute("candidate", new Candidate());
		return "candidateregister";
	}
	 
	@PostMapping("/gocandidateregister")
	public String getCandidateStatus(@Valid @ModelAttribute Candidate candidate, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "candidateregister";
		}
		candidateService.saveCandidate(candidate);
		return "redirect:/candidatelist";
	}
	
	@GetMapping("/candidatelist")
	public String viewCandidateList(Model model) {
	    List<Candidate> candidates = candidateService.candidatelist();
	    model.addAttribute("candidates", candidates);
	    return "candidatelist";
	}
	
	@GetMapping("/candidatelistVoter")
	public String viewCandidateListVoter(Model model) {
	    List<Candidate> candidates = candidateService.candidatelist();
	    model.addAttribute("candidates", candidates);
	    return "candidatelistVoter";
	}
	
	@GetMapping("/candidatelistCandidate")
	public String viewCandidateListCandidate(Model model) {
	    List<Candidate> candidates = candidateService.candidatelist();
	    model.addAttribute("candidates", candidates);
	    return "candidatelistCandidate";
	}
	
	 @PostMapping("/candidatelogin")
	    public String handleCandidateLogin1(@ModelAttribute Candidate candidate, Model model) {
	        Optional<Candidate> existingCandidateOpt1 = candidateRepo.findByUsername(candidate.getUsername());
	        System.out.println("Login attempt for username: " + candidate.getUsername());
	        System.out.println("Found candidate: " + existingCandidateOpt1);

	        if (existingCandidateOpt1.isPresent()) {
	            Candidate existingCandidate = existingCandidateOpt1.get();
	            System.out.println("Password in database: " + existingCandidate.getPassword());
	            System.out.println("Password provided: " + candidate.getPassword());

	            if (existingCandidate.getPassword().equals(candidate.getPassword())) {
	                return "redirect:/candidatedashboard";
	            } else {
	                System.out.println("Password mismatch");
	            }
	        } else {
	            System.out.println("Candidate not found");
	        }
	        model.addAttribute("error", "Invalid username or password");
	        return "candidatelogin";
	    }


	    @GetMapping("/candidatedashboard")
	    public String index(Model model) {
	        model.addAttribute("voter", new Voter());
	        return "candidatedashboard";
	    }

	    @GetMapping("/candidatelogin")
	    public String showCandidateLoginForm(Model model) {
	        model.addAttribute("candidate", new Candidate());
	        return "candidatelogin";
	    }
	    
	    @PostMapping("/deletecandidate")
	    public String deleteCandidate(@RequestParam("candidateId") int candidateId, RedirectAttributes redirectAttributes) {
	        boolean exists = candidateService.existsById(candidateId); // Assuming you have this method in your service
	        if (exists) {
	            candidateService.deleteById(candidateId);
	            return "redirect:/candidatelist";
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "Candidate ID does not exist.");
	            return "redirect:/deleteCandidatePage";
	        }
	    }

	    
	    @GetMapping("/deleteCandidatePage")
	    public String showDeleteCandidatePage() {
	        return "deletecandidate";
	    }
	    
	    @GetMapping("/viewCandidateByIdPage")
	    public String showViewCandidateByIdPage() {
	        return "viewCandidateById";
	    }

	    @GetMapping("/viewCandidateById")
	    public String viewCandidateById(@RequestParam("candidateId") int candidateId, Model model, RedirectAttributes redirectAttributes) {
	        Optional<Candidate> candidateOpt = candidateService.findById(candidateId); // Assuming you have this method in your service
	        if (candidateOpt.isPresent()) {
	            model.addAttribute("candidate", candidateOpt.get());
	            model.addAttribute("successMessage", "Candidate found!");
	            return "viewCandidateById";
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "Candidate ID does not exist.");
	            return "redirect:/viewCandidateByIdPage";
	        }
	    }
	    
	    @GetMapping("/updateCandidateIdPage")
	    public String showUpdateCandidateIdPage() {
	        return "updateCandidateId";
	    }

	    // New method to handle form submission for entering candidate ID
	    @PostMapping("/updateCandidateId")
	    public String updateCandidateId(@RequestParam("candidateId") int candidateId, RedirectAttributes redirectAttributes) {
	        boolean exists = candidateService.existsById(candidateId);
	        if (exists) {
	            return "redirect:/updateCandidatePage?id=" + candidateId;
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "Candidate ID does not exist.");
	            return "redirect:/updateCandidateIdPage";
	        }
	    }

	    @GetMapping("/updateCandidatePage")
	    public String showUpdateCandidatePage(@RequestParam("id") int id, Model model) {
	        Optional<Candidate> candidateOpt = candidateService.findById(id);
	        if (candidateOpt.isPresent()) {
	            model.addAttribute("candidate", candidateOpt.get());
	            return "updateCandidate";
	        } else {
	            model.addAttribute("errorMessage", "Candidate not found.");
	            return "redirect:/candidatelist";
	        }
	    }

	    @PostMapping("/updateCandidate")
	    public String updateCandidate(@ModelAttribute Candidate candidate, @RequestParam("id") int id, RedirectAttributes redirectAttributes) {
	        boolean exists = candidateService.existsById(id);
	        if (exists) {
	            Candidate updatedCandidate = candidateService.updateCandidate(candidate, id);
	            if (updatedCandidate != null) {
	                redirectAttributes.addFlashAttribute("successMessage", "Candidate updated successfully.");
	            } else {
	                redirectAttributes.addFlashAttribute("errorMessage", "Failed to update candidate. Please try again.");
	            }
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "Candidate ID does not exist.");
	        }
	        return "redirect:/candidatelist";
	    }
	    
	    //----------------------------------------------------------
	    @GetMapping("/updateCandidateIdPageCandidate")
	    public String showUpdateCandidateIdPageCandidate() {
	        return "updateCandidateIdCandidate";
	    }

	    // New method to handle form submission for entering candidate ID
	    @PostMapping("/updateCandidateIdCandidate")
	    public String updateCandidateIdCandidate(@RequestParam("candidateId") int candidateId, RedirectAttributes redirectAttributes) {
	        boolean exists = candidateService.existsById(candidateId);
	        if (exists) {
	            return "redirect:/updateCandidatePageCandidate?id=" + candidateId;
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "Candidate ID does not exist.");
	            return "redirect:/updateCandidateIdPageCandidate";
	        }
	    }

	    @GetMapping("/updateCandidatePageCandidate")
	    public String showUpdateCandidatePageCandidate(@RequestParam("id") int id, Model model) {
	        Optional<Candidate> candidateOpt = candidateService.findById(id);
	        if (candidateOpt.isPresent()) {
	            model.addAttribute("candidate", candidateOpt.get());
	            return "updateCandidateCandidate";
	        } else {
	            model.addAttribute("errorMessage", "Candidate not found.");
	            return "redirect:/candidatelist";
	        }
	    }

	    @PostMapping("/updateCandidateCandidate")
	    public String updateCandidateCandidate(@ModelAttribute Candidate candidate, @RequestParam("id") int id, RedirectAttributes redirectAttributes) {
	        boolean exists = candidateService.existsById(id);
	        if (exists) {
	            Candidate updatedCandidate = candidateService.updateCandidate(candidate, id);
	            if (updatedCandidate != null) {
	                redirectAttributes.addFlashAttribute("successMessage", "Candidate updated successfully.");
	            } else {
	                redirectAttributes.addFlashAttribute("errorMessage", "Failed to update candidate. Please try again.");
	            }
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "Candidate ID does not exist.");
	        }
	        return "redirect:/candidatedashboard";
	    }

}
