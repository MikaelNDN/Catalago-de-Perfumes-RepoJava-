package com.mcosmeticos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor

public class Perfume {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String marca;
    private String categoria;
    private Double preco;
    private String imagem;
    private String descricao;
    private boolean esgotado;
    private boolean promocao;

    public Perfume() {
    }

    public String getImagemPequena() {
        if (imagem == null || imagem.isEmpty()) {
            return "/img/sem-foto.png";
        }

        return imagem.replace("/upload/", "/upload/w_300,h_300,c_fill,q_auto/");
    }

    public Perfume(int id, String nome, String marca, String categoria, double preco, String imagem, String descricao, boolean esgotado, boolean promocao) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.categoria = categoria;
        this.preco = preco;
        this.imagem = imagem;
        this.descricao = descricao;
        this.esgotado = esgotado;
        this.promocao = promocao;
    }

}