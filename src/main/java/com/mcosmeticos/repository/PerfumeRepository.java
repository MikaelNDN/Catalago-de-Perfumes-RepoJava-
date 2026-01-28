package com.mcosmeticos.repository;

import com.mcosmeticos.model.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PerfumeRepository extends JpaRepository<Perfume, Integer> {

    List<Perfume> findByCategoria(String categoria);

    List<Perfume> findByNomeContainingIgnoreCaseOrMarcaContainingIgnoreCase(String nome, String marca);
}