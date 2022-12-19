package br.com.banco.controller;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.service.ContaService;
import br.com.banco.service.TransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("transferencias")
@RequiredArgsConstructor
public class TransferenciaController {

    private final TransferenciaService transferenciaService;
    private final ContaService contaService;


    @PostMapping
    public ResponseEntity<Transferencia> save(@RequestBody Transferencia transferencia) {
        Conta conta = contaService.findByIdOrThrowAnException(transferencia.getConta().getId());
        transferencia.setConta(conta);
        return new ResponseEntity<>(transferenciaService.create(transferencia), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Transferencia>> listAll() {
        return ResponseEntity.ok(transferenciaService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<Transferencia>> listById(
            Pageable pageable,
            @PathVariable Long id,
            @RequestParam(defaultValue = "1970-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicio,
            @RequestParam(defaultValue = "2030-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFim,
            @RequestParam(defaultValue = "") String operador) {
        if (!operador.equals("") && (dataInicio.getYear() != 1970 || dataFim.getYear() != 2030)){
            return ResponseEntity.ok(transferenciaService.listById(pageable, id, operador, dataInicio, dataFim));
        }

        if(operador.equals("") && (dataInicio.getYear() != 1970 || dataFim.getYear() != 2030)){
            return ResponseEntity.ok(transferenciaService.listById(pageable, id, dataInicio, dataFim));
        }

        if (!operador.equals("")) return ResponseEntity.ok(transferenciaService.listById(pageable, id, operador));
        return ResponseEntity.ok(transferenciaService.listById(pageable,id));
    }
}
