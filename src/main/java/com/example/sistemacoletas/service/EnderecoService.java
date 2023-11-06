package com.example.sistemacoletas.service;

import com.example.sistemacoletas.entities.Endereco;
import com.example.sistemacoletas.entities.UsuarioEndereco;
import com.example.sistemacoletas.repository.EnderecoRepository;
import com.example.sistemacoletas.repository.UsuarioEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final UsuarioEnderecoRepository usuarioEnderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository, UsuarioEnderecoRepository usuarioEnderecoRepository) {
        this.enderecoRepository = enderecoRepository;
        this.usuarioEnderecoRepository = usuarioEnderecoRepository;
    }

    public Optional<Endereco> findEnderecoByUsuarioId(Long usuarioId) {
        UsuarioEndereco usuarioEndereco = usuarioEnderecoRepository.findByUsuarioUsuarioId(usuarioId);
        if (usuarioEndereco != null && usuarioEndereco.getEndereco() != null) {
            return Optional.of(usuarioEndereco.getEndereco());
        }
        return Optional.empty();
    }

    public Optional<Endereco> findEnderecoById(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco saveOrUpdateEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }
}
