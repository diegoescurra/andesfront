package com.example.andesfront.controller;

import com.example.andesfront.dto.AlumnoDTO;
import com.example.andesfront.dto.UserDTO;
import com.example.andesfront.service.AlumnoService;
import com.example.andesfront.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDTO userDTO, HttpSession session, Model model) {
        System.out.println(userDTO.toString());
        String token = userService.signin(userDTO);
        model.addAttribute("token", token);
        return "redirect:/home";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidar la sesión
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(Model model) {

        List<AlumnoDTO> alumnos = alumnoService.getAlumnos();
        model.addAttribute("alumnos", alumnos);

        return "home";
    }

}
