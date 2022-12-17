package br.com.banco.controller;

import br.com.banco.dto.ContaDTO;
import br.com.banco.model.Conta;
import br.com.banco.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("contas")
@RequiredArgsConstructor
public class ContaController {
    private final ContaService contaService;

    @GetMapping
    public ResponseEntity<Page<Conta>> list(Pageable pageable){
        return ResponseEntity.ok(contaService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Conta> save(@RequestBody @Valid ContaDTO contaDTO){
        return new ResponseEntity<>(contaService.save(contaDTO), HttpStatus.CREATED);
    }

}
