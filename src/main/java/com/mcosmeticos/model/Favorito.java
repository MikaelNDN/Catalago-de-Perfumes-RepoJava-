package com.mcosmeticos.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;

    public Favorito(Usuario usuario, Perfume perfume) {
        this.usuario = usuario;
        this.perfume = perfume;
    }
}