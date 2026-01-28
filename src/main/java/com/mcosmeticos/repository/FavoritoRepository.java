package com.mcosmeticos.repository;

import com.mcosmeticos.model.Favorito;
import com.mcosmeticos.model.Perfume;
import com.mcosmeticos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    Favorito findByUsuarioAndPerfume(Usuario usuario, Perfume perfume);

    List<Favorito> findByUsuario(Usuario usuario);
}