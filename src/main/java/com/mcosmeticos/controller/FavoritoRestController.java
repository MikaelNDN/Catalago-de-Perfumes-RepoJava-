package com.mcosmeticos.controller;

import com.mcosmeticos.model.Usuario;
import com.mcosmeticos.service.FavoritoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoRestController {

    @Autowired private FavoritoService favoritoService;

    @PostMapping("/toggle/{perfumeId}")
    public ResponseEntity<Boolean> toggle(@PathVariable int perfumeId, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return ResponseEntity.status(401).build();
        }
        boolean agoraEhFavorito = favoritoService.toggleFavorito(usuario, perfumeId);
        return ResponseEntity.ok(agoraEhFavorito);
    }

    @GetMapping("/meus-ids")
    public ResponseEntity<List<Integer>> meusFavoritos(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity.ok(favoritoService.listarIdsFavoritos(usuario));
    }
}