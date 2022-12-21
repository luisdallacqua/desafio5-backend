package br.com.banco.util;

import br.com.banco.dto.Transferencia.TransferenciaDTO;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;

import java.math.BigDecimal;

import static br.com.banco.util.DateFormatter.*;

public class TransferenciaCreator {

    public static Transferencia createTransferencia(){
        return Transferencia.builder()
                .id(1L)
                .dataTransferencia(formatDateToZonaDateTime("2019-01-01"))
                .valor(new BigDecimal("40895.46"))
                .tipo("DEPOSITO")
                .nomeOperadorTransacao(null)
                .conta(Conta.builder()
                        .id(1L)
                        .nomeResponsavel("Fulano")
                        .build())
                .build();
    }

    public static Transferencia createTransferenciaWithOperator(){
        return Transferencia.builder()
                .id(1L)
                .dataTransferencia(formatDateToZonaDateTime("2020-06-06"))
                .valor(new BigDecimal("30895.46"))
                .tipo("DEPOSITO")
                .nomeOperadorTransacao("Beltrano")
                .conta(Conta.builder()
                        .id(1L)
                        .nomeResponsavel("Fulano")
                        .build())
                .build();
    }
    public static TransferenciaDTO createTransferenciaDTOAnd2019(){
        return TransferenciaDTO.builder()
                .id(1L)
                .dataTransferencia(formatDateToZonaDateTime("2019-01-01"))
                .valor(new BigDecimal("20895.46"))
                .tipo("SAQUE")
                .nomeOperadorTransacao(null)
                .build();
    }

    public static TransferenciaDTO transferenciaDTOSWithOperatorAnd2020(){
        return TransferenciaDTO.builder()
                .id(1L)
                .dataTransferencia(formatDateToZonaDateTime("2020-06-06"))
                .valor(new BigDecimal("10895.46"))
                .tipo("DEPOSITO")
                .nomeOperadorTransacao("Fulano")
                .build();
    }

}
