package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LogInController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/LogIn")
    public String getLogin(Model model){
        return ("login");
    }

    @PostMapping("/LogIn/processLogin")
    public String processForm(HttpSession session, @RequestParam String email, @RequestParam String password){
        Optional<User> tryUser = userRepository.findByMail(email);
        if (tryUser.isPresent() && passwordEncoder.matches(password, tryUser.get().getPassword())) {
                session.setAttribute("user", tryUser.get().getId());
                session.setAttribute("logged", true);
                return "redirect:/home";
        }
        return "redirect:/LogIn";
    }

    @GetMapping("/LogIn/processLogout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/home";
    }
}
