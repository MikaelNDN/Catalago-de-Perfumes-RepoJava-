package com.mcosmeticos.controller;

import org.springframework.ui.Model;
import com.mcosmeticos.model.Perfume;
import com.mcosmeticos.service.PerfumeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PerfumeService perfumeService;

    @GetMapping("/")
    public String home(HttpSession session,
                       Model model, @RequestParam(required = false) Integer destaqueId, String categoria) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/login";
        }
        List<Perfume> produtosFiltrados = perfumeService.listarPorCategoria(categoria);
        model.addAttribute("perfumes", produtosFiltrados);
        model.addAttribute("categoriaAtiva", categoria);

        model.addAttribute("perfumes", perfumeService.listarTodos());

        if (destaqueId != null) {
            Perfume destaque = perfumeService.buscarPorId(destaqueId);
            model.addAttribute("produtoAtual", destaque);
        }
        return "home";

    }



    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
