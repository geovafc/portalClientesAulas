package br.com.coderbank.portalcliente.controllers;

import br.com.coderbank.portalcliente.dtos.requests.ClienteRequestDTO;
import br.com.coderbank.portalcliente.dtos.responses.ClienteResponseDTO;
import br.com.coderbank.portalcliente.services.ClienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/clientes")
@Slf4j
public class ClienteControllerV2 {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        log.info("m=salvar, ClienteRequestDTO={}", clienteRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.salvar(clienteRequestDTO));
    }

}
