package br.com.banco.controller;

import br.com.banco.dto.Conta.ContaDTO;
import br.com.banco.model.Conta;
import br.com.banco.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping
    public ResponseEntity<Conta> save(@RequestBody @Valid ContaDTO contaDTO){
        return new ResponseEntity<>(contaService.save(contaDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        contaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> replace(@PathVariable Long id,
            @RequestBody ContaDTO contaDTO) {
        contaService.replace(id, contaDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
