package br.com.banco.controller;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.service.TransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transferencias")
@RequiredArgsConstructor
public class TransferenciaController {

    private TransferenciaService transferenciaService;

    @PostMapping
    public ResponseEntity<Transferencia> save(@RequestBody Transferencia transferencia){
        return new ResponseEntity<>(transferenciaService.create(transferencia), HttpStatus.CREATED);
    }
}
