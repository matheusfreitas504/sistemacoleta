package com.example.sistemacoletas.controller;

import com.example.sistemacoletas.entities.Usuario;
import com.example.sistemacoletas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.findAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioService.findUsuarioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok(savedUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.findUsuarioById(id)
                .map(existingUser -> {
                    existingUser.setEmailLogin(usuario.getEmailLogin());
                    existingUser.setNome(usuario.getNome());
                    existingUser.setTelefone(usuario.getTelefone());
                    existingUser.setFoto(usuario.getFoto());
                    existingUser.setCpf(usuario.getCpf());
                    existingUser.setCnpj(usuario.getCnpj());
                    existingUser.setSenha(usuario.getSenha());
                    existingUser.setStatus(usuario.getStatus());
                    existingUser.setUsuarioTipo(usuario.getUsuarioTipo());
                    Usuario updatedUsuario = usuarioService.saveUsuario(existingUser);
                    return ResponseEntity.ok(updatedUsuario);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok().build();
    }

    // Adicione outros endpoints conforme necessário
}

