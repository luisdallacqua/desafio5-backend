package br.com.banco.controller;

import br.com.banco.model.Conta;
import br.com.banco.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contas")
@RequiredArgsConstructor
public class ContaController {
    private final ContaService contaService;

    @GetMapping
    public ResponseEntity<List<Conta>> list(){
        return ResponseEntity.ok(contaService.listAll());
    }

    @PostMapping
    public ResponseEntity<Conta> save(@RequestBody Conta conta){
        return new ResponseEntity<>(contaService.save(conta), HttpStatus.CREATED);
    }

}
