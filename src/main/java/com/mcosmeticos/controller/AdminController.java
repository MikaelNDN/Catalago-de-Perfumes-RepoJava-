package com.mcosmeticos.controller;

import com.mcosmeticos.model.Perfume;
import com.mcosmeticos.model.Usuario;
import com.mcosmeticos.service.CloudinaryService;
import com.mcosmeticos.service.PerfumeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PerfumeService perfumeService;

    @Autowired
    private CloudinaryService cloudinaryService;


    @GetMapping
    public String adminPage(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null || !usuario.isAdmin()) return "redirect:/";

        model.addAttribute("perfume", new Perfume());
        model.addAttribute("lista", perfumeService.listarTodos());
        return "admin";
    }

    @PostMapping("/salvar")
    public String salvarPerfume(@ModelAttribute Perfume perfume,
                                @RequestParam("file") MultipartFile file,
                                HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null || !usuario.isAdmin()) return "redirect:/";

        if (!file.isEmpty()) {
            try {

                String urlDaImagem = cloudinaryService.uploadImagem(file);

                perfume.setImagem(urlDaImagem);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (perfume.getId() > 0) {
            Perfume antigo = perfumeService.buscarPorId(perfume.getId());
            if (antigo != null) {
                perfume.setImagem(antigo.getImagem());
            }
        }

        perfumeService.salvar(perfume);
        return "redirect:/admin";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable int id, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario != null && usuario.isAdmin()) perfumeService.deletar(id);
        return "redirect:/admin";
    }

    @GetMapping("/toggleEsgotado/{id}")
    public String toggleEsgotado(@PathVariable int id, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario != null && usuario.isAdmin()) {
            Perfume p = perfumeService.buscarPorId(id);
            if (p != null) {
                p.setEsgotado(!p.isEsgotado());
                perfumeService.salvar(p);
            }
        }
        return "redirect:/admin";
    }
}