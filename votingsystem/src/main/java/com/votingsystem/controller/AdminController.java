package com.votingsystem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.votingsystem.entity.Admin;
import com.votingsystem.repository.Adminrepository;
import com.votingsystem.service.AdminService;

import jakarta.validation.Valid;

@Controller
public class AdminController {
    
    @Autowired
    Adminrepository adminRepository;
    
    @Autowired
    AdminService adminService;
    
    
    @GetMapping("/index")
    public String index1(Model model) {
        model.addAttribute("admin", new Admin());
        return "index";
    }
    
    @GetMapping("/saveAdmin")
    public String saveAdmin(Model model) {
        model.addAttribute("admin", new Admin());
        return "adminregister";
    }
    
    @PostMapping("/goadminregister")
    public String getAdminStatus(@Valid @ModelAttribute Admin admin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "adminregister";
        }
        adminService.saveAdmin(admin);
        return "redirect:/adminlogin";
    }
    
    @PostMapping("/adminlogin")
    public String handleAdminLogin1(@ModelAttribute Admin admin, Model model) {
        Optional<Admin> existingAdminOpt1 = adminRepository.findByUsername(admin.getUsername());
        System.out.println("Login attempt for username: " + admin.getUsername());
        System.out.println("Found admin: " + existingAdminOpt1);

        if (existingAdminOpt1.isPresent()) {
            Admin existingAdmin = existingAdminOpt1.get();
            System.out.println("Password in database: " + existingAdmin.getPassword());
            System.out.println("Password provided: " + admin.getPassword());

            if (existingAdmin.getPassword().equals(admin.getPassword())) {
                return "redirect:/admindashboard";
            } else {
                System.out.println("Password mismatch");
            }
        } else {
            System.out.println("Admin not found");
        }
        model.addAttribute("error", "Invalid username or password");
        return "adminlogin";
    }


    @GetMapping("/admindashboard")
    public String index(Model model) {
        model.addAttribute("admin", new Admin());
        return "admindashboard";
    }

    @GetMapping("/adminlogin")
    public String showAdminLoginForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "adminlogin";
    }
    
}
