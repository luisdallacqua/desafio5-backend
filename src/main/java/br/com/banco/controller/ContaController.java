package br.com.banco.controller;

import br.com.banco.model.Conta;
import br.com.banco.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contas")
@RequiredArgsConstructor
public class ContaController {
    private final ContaService contaService;

    @GetMapping
    public ResponseEntity<Page<Conta>> list(Pageable pageable){
        return ResponseEntity.ok(contaService.listAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contaService.findByIdOrThrowAnException(id));
    }

}
