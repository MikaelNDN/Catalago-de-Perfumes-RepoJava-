package com.mcosmeticos.service;

import com.mcosmeticos.model.Perfume;
import com.mcosmeticos.repository.PerfumeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfumeService {

    @Autowired
    private PerfumeRepository repo;

    public void salvar(Perfume perfume) {
        if (perfume.getImagem() == null || perfume.getImagem().isEmpty()) {
            perfume.setImagem("/logo.png");
        }
        repo.save(perfume);
    }


    public List<Perfume> listarTodos() {
        return repo.findAll();
    }

    public List<Perfume> listarPorCategoria(String categoria) {
        if (categoria == null || categoria.equals("Todos")) {
            return repo.findAll();
        }
        return repo.findByCategoria(categoria);
    }

    public void deletar(int id) {
        repo.deleteById(id);
    }

    public Perfume buscarPorId(int id) {
        return repo.findById(id).orElse(null);
    }

    public List<Perfume> filtrarPersonalizado(String categoria, String termo) {
        List<Perfume> resultados;

        if (termo != null && !termo.isEmpty()) {
            resultados = repo.findByNomeContainingIgnoreCaseOrMarcaContainingIgnoreCase(termo, termo);
        } else {
            resultados = repo.findAll();
        }

        if (categoria != null && !categoria.equals("Todos")) {
            resultados = resultados.stream()
                    .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                    .toList();
        }

        return resultados;
    }

    @PostConstruct
    public void carregarDadosIniciais() {
        if (repo.count() == 0) { // Só cadastra se o banco estiver vazio
            repo.save(new Perfume(0, "Empire Noir", "Empire", "Hinode", 129.9, "/empire1.png", "Aromas intensos.", false, false));
            repo.save(new Perfume(0, "Empire Fresh", "Empire", "Hinode", 119.9, "/empire2.png", "Notas frescas.", false, true));
            repo.save(new Perfume(0, "Essencial Bloom", "Essencial", "Natura", 99.9, "/essencial1.png", "Clássico.", true, false));
            repo.save(new Perfume(0, "Malbec", "Malbec", "O Boticario", 189.9, "/malbec.png", "Amadeirado.", false, false));

            System.out.println("--- BANCO DE DADOS POPULADO COM SUCESSO ---");
        }
    }
}