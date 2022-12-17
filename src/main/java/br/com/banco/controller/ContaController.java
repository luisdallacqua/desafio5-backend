package br.com.banco.controller;

import br.com.banco.dto.Conta.ContaPostDTO;
import br.com.banco.dto.Conta.ContaPutDTO;
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
    public ResponseEntity<Conta> save(@RequestBody @Valid ContaPostDTO contaPostDTO){
        return new ResponseEntity<>(contaService.save(contaPostDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        contaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Void> replace(@RequestBody ContaPutDTO contaPutDTO) {
        contaService.replace(contaPutDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
