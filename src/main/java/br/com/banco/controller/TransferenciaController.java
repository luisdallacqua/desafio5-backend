package br.com.banco.controller;

import br.com.banco.dto.Transferencia.TransferenciaDTO;
import br.com.banco.service.TransferenciaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin
@RequestMapping("transferencias")
@RequiredArgsConstructor
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a Pageable List of Transfer if Successfull Operation"),
            @ApiResponse(responseCode = "400", description = "If count related to transfer is not found")
    })
    public ResponseEntity<Page<TransferenciaDTO>> listByContaId(
            @ParameterObject
            Pageable pageable,
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "1970-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicio,
            @RequestParam(required = false, defaultValue = "2030-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFim,
            @RequestParam(required = false, defaultValue = "") String operador) {
        if (!operador.equals("") && (dataInicio.getYear() != 1970 || dataFim.getYear() != 2030)) {
            return ResponseEntity.ok(
                    transferenciaService
                            .listTransferenciasByConta_IdAndNomeOperadorTransacaoAndDataTransferenciaBetween(
                                    pageable,
                                    id,
                                    operador,
                                    dataInicio,
                                    dataFim)
            );
        }
        if (operador.equals("") && (dataInicio.getYear() != 1970 || dataFim.getYear() != 2030)) {
            return ResponseEntity.ok(
                    transferenciaService
                            .listTransferenciasByConta_IdAndDataTransferenciaBetween(
                                    pageable,
                                    id,
                                    dataInicio,
                                    dataFim)
            );
        }
        if (!operador.equals("")) {
            return ResponseEntity.ok(transferenciaService
                    .listTransferenciasByConta_IdAndNomeOperadorTransacao(pageable, id, operador));
        }
        return ResponseEntity.ok(transferenciaService.listByContaId(pageable, id));
    }
}
