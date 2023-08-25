package com.isi2.examenspring.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Authentication authentication) {
        System.out.println("Hi mi je suis dans le dashboard");
        // Vous pouvez utiliser l'objet Authentication pour obtenir des informations sur l'utilisateur
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Rediriger l'utilisateur vers le tableau de bord
        return "dashboard"; // Assurez-vous que l'URL est correcte
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password,
                               @RequestParam String redirect, HttpServletRequest request) {
        System.out.println("EHOOOOOOO");
        return "redirect:/Dashboard";
    }

}
