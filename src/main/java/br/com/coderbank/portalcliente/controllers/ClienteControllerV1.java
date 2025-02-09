package br.com.coderbank.portalcliente.controllers;


import br.com.coderbank.portalcliente.entities.Cliente;
import br.com.coderbank.portalcliente.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteControllerV1 {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {

        var clienteResponse = clienteRepository.save(cliente);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteResponse);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obterTodos() {
        return ResponseEntity.ok(clienteRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable
                                               UUID id) {

        var cliente = clienteRepository.findById(id)
                .orElse(null);

        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable(value = "id") UUID id,
                                             @RequestBody Cliente cliente) {

        var clienteOptional = clienteRepository.findById(id);

        return clienteOptional
                .map(c -> ResponseEntity.ok(
                        clienteRepository.save(cliente)
                ))

                .orElseGet(() -> ResponseEntity.notFound().build());

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        clienteRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
