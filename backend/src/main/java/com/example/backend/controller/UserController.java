package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/profile/register")
    public String registerUser(@RequestParam("username") String username, @RequestParam("password") String password,
                               @RequestParam("email") String email, HttpSession session) {

        User user = new User(email, username, passwordEncoder.encode(password),
                false, null, new ArrayList<>());

        userRepository.save(user);
        session.setAttribute("user", user);
        session.setAttribute("logged", true);
        return "redirect:/home";
    }

    @GetMapping("/profile")
    public String getProfile(Model model){return ("profile");}
}
