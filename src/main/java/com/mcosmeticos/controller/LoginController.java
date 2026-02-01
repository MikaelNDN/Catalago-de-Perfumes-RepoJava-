package com.mcosmeticos.controller;

import com.mcosmeticos.model.Usuario;
import com.mcosmeticos.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {

        Usuario usuario = usuarioService.autenticar(email, password);

        if (usuario != null) {
            session.setAttribute("usuarioLogado", usuario);
            session.setMaxInactiveInterval(1800);


            if (usuario.isAdmin()) {
                return "redirect:/admin";
            } else {
                return "redirect:/";
            }
        }

        model.addAttribute("error", "E-mail ou senha inválidos!");
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastroPage() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String doCadastro(@RequestParam String nome,
                             @RequestParam String email,
                             @RequestParam String password) {
        try {
            usuarioService.cadastrarCliente(nome, email, password);
            return "redirect:/login";
        } catch (Exception e) {
            return "redirect:/cadastro?erro=true";
        }
    }
}