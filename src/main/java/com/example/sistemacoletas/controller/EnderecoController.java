package com.example.sistemacoletas.controller;

import com.example.sistemacoletas.entities.Endereco;
import com.example.sistemacoletas.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios/{usuarioId}/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<Endereco> getEnderecoByUsuarioId(@PathVariable Long usuarioId) {
        return enderecoService.findEnderecoByUsuarioId(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{enderecoId}")
    public ResponseEntity<Endereco> updateEndereco(@PathVariable Long enderecoId, @RequestBody Endereco enderecoDetails) {
        return enderecoService.findEnderecoById(enderecoId).map(endereco -> {
            endereco.setPlaceId(enderecoDetails.getPlaceId());
            endereco.setRua(enderecoDetails.getRua());
            endereco.setNumero(enderecoDetails.getNumero());
            endereco.setComplemento(enderecoDetails.getComplemento());
            endereco.setCep(enderecoDetails.getCep());
            endereco.setBairro(enderecoDetails.getBairro());
            endereco.setCidade(enderecoDetails.getCidade());
            endereco.setEstado(enderecoDetails.getEstado());
            Endereco updatedEndereco = enderecoService.saveOrUpdateEndereco(endereco);
            return ResponseEntity.ok(updatedEndereco);
        }).orElse(ResponseEntity.notFound().build());
    }
}
