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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.votingsystem.entity.Admin;
import com.votingsystem.entity.Voter;
import com.votingsystem.repository.VoterRepository;
import com.votingsystem.service.VoterService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class VoterController {
	
	@Autowired
	VoterService voterService;
	
	@Autowired
	VoterRepository voterRepo;
	
	
	@GetMapping("/saveVoter")
	public String saveVoter(Model model) {
		model.addAttribute("voter", new Voter());
		return "VoterRegistration";
	}
	 
	@PostMapping("/govoterregister")
	public String getVoterStatus(@Valid @ModelAttribute Voter voter, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "VoterRegistration";
		}
		voterService.saveVoter(voter);
		return "redirect:/voterlist";
	}
	
	@GetMapping("/voterlist")
	public String viewVoterList(Model model) {
	    List<Voter> voters = voterService.voterlist();
	    model.addAttribute("voters", voters);
	    return "voterlist";
	}
	
	@GetMapping("/voterlistCandidate")
	public String viewVoterListCandidate(Model model) {
	    List<Voter> voters = voterService.voterlist();
	    model.addAttribute("voters", voters);
	    return "voterlistcandidate";
	}
	 @PostMapping("/voterlogin")
	    public String handleVoterLogin1(@ModelAttribute Voter voter, Model model, HttpSession session) {
	        Optional<Voter> existingVoterOpt1 = voterRepo.findByUsername(voter.getUsername());
	        System.out.println("Login attempt for username: " + voter.getUsername());
	        System.out.println("Found voter: " + existingVoterOpt1);

	        if (existingVoterOpt1.isPresent()) {
	            Voter existingVoter = existingVoterOpt1.get();
	            System.out.println("Password in database: " + existingVoter.getPassword());
	            System.out.println("Password provided: " + voter.getPassword());

	            if (existingVoter.getPassword().equals(voter.getPassword())) {
	                session.setAttribute("voter", existingVoter);
	                return "redirect:/voterdashboard";
	            } else {
	                System.out.println("Password mismatch");
	            }
	        } else {
	            System.out.println("Voter not found");
	        }
	        model.addAttribute("error", "Invalid username or password");
	        return "voterlogin";
	    }

	    @GetMapping("/voterdashboard")
	    public String index(Model model) {
	        model.addAttribute("voter", new Voter());
	        return "voterdashboard";
	    }
	    @GetMapping("/voterlogin")
	    public String showAdminLoginForm(Model model) {
	        model.addAttribute("voter", new Voter());
	        return "voterlogin";
	    }
	    
	    @PostMapping("/deletevoter")
	    public String deleteVoter(@RequestParam("voterId") int voterId, RedirectAttributes redirectAttributes) {
	        boolean exists = voterService.existsById(voterId); // Assuming you have this method in your service
	        if (exists) {
	            voterService.deleteById(voterId);
	            return "redirect:/voterlist";
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "Voter ID does not exist.");
	            return "redirect:/deleteVoterPage";
	        }
	    }

	    
	    @GetMapping("/deleteVoterPage")
	    public String showDeleteVoterPage() {
	        return "deletevoter";
	    }
	    
	    @GetMapping("/viewVoterByIdPage")
	    public String showViewVoterByIdPage() {
	        return "viewVoterById";
	    }

	    @GetMapping("/viewVoterById")
	    public String viewVoterById(@RequestParam("voterId") int voterId, Model model, RedirectAttributes redirectAttributes) {
	        Optional<Voter> voterOpt = voterService.findById(voterId); // Assuming you have this method in your service
	        if (voterOpt.isPresent()) {
	            model.addAttribute("voter", voterOpt.get());
	            model.addAttribute("successMessage", "Voter found!");
	            return "viewVoterById";
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "Voter ID does not exist.");
	            return "redirect:/viewVoterByIdPage";
	        }
	    }

	    
	    @GetMapping("/updateVoterIdPage")
	    public String showUpdateVoterIdPage() {
	        return "updateVoterId";
	    }

	    // New method to handle form submission for entering voter ID
	    @PostMapping("/updateVoterId")
	    public String updateVoterId(@RequestParam("voterId") int voterId, RedirectAttributes redirectAttributes) {
	        boolean exists = voterService.existsById(voterId);
	        if (exists) {
	            return "redirect:/updateVoterPage?id=" + voterId;
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "Voter ID does not exist.");
	            return "redirect:/updateVoterIdPage";
	        }
	    }

	    @GetMapping("/updateVoterPage")
	    public String showUpdateVoterPage(@RequestParam("id") int id, Model model) {
	        Optional<Voter> voterOpt = voterService.findById(id);
	        if (voterOpt.isPresent()) {
	            model.addAttribute("voter", voterOpt.get());
	            return "updateVoter";
	        } else {
	            model.addAttribute("errorMessage", "Voter not found.");
	            return "redirect:/voterlist";
	        }
	    }

	    @PostMapping("/updateVoter")
	    public String updateVoter(@ModelAttribute Voter voter, @RequestParam("id") int id, RedirectAttributes redirectAttributes) {
	        boolean exists = voterService.existsById(id);
	        if (exists) {
	            Voter updatedVoter = voterService.updateVoter(voter, id);
	            if (updatedVoter != null) {
	                redirectAttributes.addFlashAttribute("successMessage", "Voter updated successfully.");
	            } else {
	                redirectAttributes.addFlashAttribute("errorMessage", "Failed to update voter. Please try again.");
	            }
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "Voter ID does not exist.");
	        }
	        return "redirect:/voterlist";
	    }
	 //---------------------------------------------------------------------------------------
	    @GetMapping("/updateVoterIdPageVoter")
	    public String showUpdateVoterIdPageVoter() {
	        return "updateVoterIdVoter";
	    }

	    // New method to handle form submission for entering voter ID
	    @PostMapping("/updateVoterIdVoter")
	    public String updateVoterIdVoter(@RequestParam("voterId") int voterId, RedirectAttributes redirectAttributes) {
	        boolean exists = voterService.existsById(voterId);
	        if (exists) {
	            return "redirect:/updateVoterPageVoter?id=" + voterId;
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "Voter ID does not exist.");
	            return "redirect:/updateVoterIdPageVoter";
	        }
	    }

	    @GetMapping("/updateVoterPageVoter")
	    public String showUpdateVoterPageVoter(@RequestParam("id") int id, Model model) {
	        Optional<Voter> voterOpt = voterService.findById(id);
	        if (voterOpt.isPresent()) {
	            model.addAttribute("voter", voterOpt.get());
	            return "updateVoterVoter";
	        } else {
	            model.addAttribute("errorMessage", "Voter not found.");
	            return "redirect:/voterlist";
	        }
	    }

	    @PostMapping("/updateVoterVoter")
	    public String updateVoterVoter(@ModelAttribute Voter voter, @RequestParam("id") int id, RedirectAttributes redirectAttributes) {
	        boolean exists = voterService.existsById(id);
	        if (exists) {
	            Voter updatedVoter = voterService.updateVoter(voter, id);
	            if (updatedVoter != null) {
	                redirectAttributes.addFlashAttribute("successMessage", "Voter updated successfully.");
	            } else {
	                redirectAttributes.addFlashAttribute("errorMessage", "Failed to update voter. Please try again.");
	            }
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "Voter ID does not exist.");
	        }
	        return "redirect:/voterdashboard";
	    }
	    
	    
	    
	    
	    
	    
	    
}
