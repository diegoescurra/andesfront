package com.example.andesfront.controller;

import com.example.andesfront.dto.AlumnoDTO;
import com.example.andesfront.dto.UserDTO;
import com.example.andesfront.service.AlumnoService;
import com.example.andesfront.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/login")
    public String iniciarSesion(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("error", error != null);
        return "login";
    }

    @PostMapping("/loginPost")
    public String login(@ModelAttribute UserDTO userDTO, HttpSession session, Model model) throws JsonProcessingException {

        String token = userService.signin(userDTO);
        ObjectMapper objectMapper = new ObjectMapper();
        Map <String,String> map = objectMapper.readValue(token, Map.class);
        String newToken = map.get("token");
        session.setAttribute("authToken", newToken);
        return "redirect:/home";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        String token = (String) session.getAttribute("authToken");

        System.out.println("Controler token:" + token);
        if(token == null || token.isEmpty()) {
            return "redirect:/login";
        }

        List<AlumnoDTO> alumnos = alumnoService.getAlumnos(token);
        model.addAttribute("alumnos", alumnos);

        return "home";
    }

}
