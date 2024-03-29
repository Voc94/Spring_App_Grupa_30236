package com.example.Final.controllers;

import com.example.Final.data.UserRepository;
import com.example.Final.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository userRepository; //dependency injection whaa
    @GetMapping
    public String displayAllUsers(Model model) {
        model.addAttribute("title", "All Users");
        model.addAttribute("users", userRepository.findAll());
        return "users/index";
    }

    @GetMapping("create")
    public String displayCreateUserForm(Model model) {
        model.addAttribute("title", "Create User");
        model.addAttribute(new User());
        return "users/create";
    }

    @PostMapping("create")
    public String processCreateUserForm(@ModelAttribute User newUser, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create User");
            return "users/create";
        } else {
            userRepository.save(newUser);
            return "redirect:/users";
        }
    }

    @GetMapping("delete")
    public String displayDeleteUserForm(Model model) {
        model.addAttribute("title", "Delete Users");
        model.addAttribute("users", userRepository.findAll());
        return "users/delete";
    }

    @PostMapping("delete")
    public String processDeleteUserForm(@RequestParam(required = false) int[] userIds) {
        if (userIds != null) {
            for (int id : userIds) {
                userRepository.deleteById(id);
            }
        }
        return "redirect:/users";
    }
}
