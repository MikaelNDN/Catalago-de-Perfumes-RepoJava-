package com.mcosmeticos.controller;

import com.mcosmeticos.model.Perfume;
import com.mcosmeticos.model.Usuario;
import com.mcosmeticos.repository.PerfumeRepository;
import com.mcosmeticos.service.FavoritoService;
import com.mcosmeticos.service.PerfumeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/perfumes")
public class PerfumeRestController {

    @Autowired private PerfumeService perfumeService;
    @Autowired private FavoritoService favoritoService;
    @Autowired private PerfumeRepository perfumeRepository;

    @GetMapping
    public List<Perfume> filtrar(@RequestParam(required = false, defaultValue = "Todos") String categoria,
                                 @RequestParam(required = false, defaultValue = "") String busca) {
        return perfumeService.filtrarPersonalizado(categoria, busca);
    }

    @GetMapping("/meus-favoritos")
    public List<Perfume> listarMeusFavoritos(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) return List.of();

        List<Integer> ids = favoritoService.listarIdsFavoritos(usuario);
        return perfumeRepository.findAllById(ids);
    }
}