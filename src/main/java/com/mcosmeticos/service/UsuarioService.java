package com.mcosmeticos.service;

import com.mcosmeticos.model.Usuario;
import com.mcosmeticos.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value; // <--- IMPORTANTE
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Value("${app.admin.senha}")
    private String senhaPadraoAdmin;

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = repo.findByEmail(email);
        if (usuario != null && BCrypt.checkpw(senha, usuario.getSenha())) {
            return usuario;
        }
        return null;
    }

    public void cadastrarCliente(String nome, String email, String senha) {
        String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());
        Usuario novo = new Usuario(nome, email, senhaHash, false);
        repo.save(novo);
    }

    @PostConstruct
    public void criarAdminPadrao() {
        if (repo.findByEmail("mazecosmeticos@gmail.com") == null) {

            String senhaHash = BCrypt.hashpw(senhaPadraoAdmin, BCrypt.gensalt());

            Usuario admin = new Usuario("Maze Noberto", "mazecosmeticos@gmail.com", senhaHash, true);
            repo.save(admin);
            System.out.println("--- ADMIN SEGURO CRIADO ---");
        }
    }
}