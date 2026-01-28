package com.mcosmeticos.service;

import com.mcosmeticos.model.Favorito;
import com.mcosmeticos.model.Perfume;
import com.mcosmeticos.model.Usuario;
import com.mcosmeticos.repository.FavoritoRepository;
import com.mcosmeticos.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritoService {

    @Autowired private FavoritoRepository favoritoRepo;
    @Autowired private PerfumeRepository perfumeRepo;

    public boolean toggleFavorito(Usuario usuario, int perfumeId) {
        Perfume perfume = perfumeRepo.findById(perfumeId).orElse(null);
        if (perfume == null || usuario == null) return false;

        Favorito existente = favoritoRepo.findByUsuarioAndPerfume(usuario, perfume);

        if (existente != null) {
            favoritoRepo.delete(existente);
            return false;
        } else {
            Favorito novo = new Favorito(usuario, perfume);
            favoritoRepo.save(novo);
            return true;
        }
    }


    public List<Integer> listarIdsFavoritos(Usuario usuario) {
        return favoritoRepo.findByUsuario(usuario).stream()
                .map(f -> f.getPerfume().getId())
                .collect(Collectors.toList());
    }
}