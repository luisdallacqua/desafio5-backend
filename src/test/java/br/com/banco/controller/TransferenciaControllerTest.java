package br.com.banco.controller;

import br.com.banco.dto.Transferencia.TransferenciaDTO;
import br.com.banco.model.Transferencia;
import br.com.banco.service.TransferenciaService;
import br.com.banco.util.TransferenciaCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static br.com.banco.util.DateUtilTest.*;

@ExtendWith(SpringExtension.class)
class TransferenciaControllerTest {

    @InjectMocks
    private TransferenciaController transferenciaController;
    @Mock
    private TransferenciaService transferenciaService;

    @BeforeEach
    void setup() {
        PageImpl<TransferenciaDTO> transferenciaDTOS = new PageImpl<>(
                List.of(TransferenciaCreator.createTransferenciaDTOAnd2019(),
                        TransferenciaCreator.transferenciaDTOSWithOperatorAnd2020()));

        PageImpl<TransferenciaDTO> transferenciaDTOSWithOperatorAnd2020 = new PageImpl<>(
                List.of(TransferenciaCreator.transferenciaDTOSWithOperatorAnd2020()));

        BDDMockito.when(transferenciaService.listByContaId(
                        ArgumentMatchers.any(),
                        ArgumentMatchers.eq(1L)))
                .thenReturn(transferenciaDTOS);

        BDDMockito.when(transferenciaService.listTransferenciasByConta_IdAndNomeOperadorTransacao(
                        ArgumentMatchers.any(),
                        ArgumentMatchers.eq(1L),
                        ArgumentMatchers.eq("Fulano")))
                .thenReturn(transferenciaDTOSWithOperatorAnd2020);

        BDDMockito.when(transferenciaService.listTransferenciasByConta_IdAndDataTransferenciaBetween(
                        ArgumentMatchers.any(),
                        ArgumentMatchers.eq(1L),
                        ArgumentMatchers.eq(LocalDate.parse("2020-01-01")),
                        ArgumentMatchers.eq(LocalDate.parse("2022-01-01"))
                ))
                .thenReturn(transferenciaDTOSWithOperatorAnd2020);

        BDDMockito.when(transferenciaService
                        .listTransferenciasByConta_IdAndNomeOperadorTransacaoAndDataTransferenciaBetween(ArgumentMatchers.any(),
                                ArgumentMatchers.eq(1L),
                                ArgumentMatchers.eq("Fulano"),
                                ArgumentMatchers.eq(LocalDate.parse("2020-01-01")),
                                ArgumentMatchers.eq(LocalDate.parse("2022-01-01"))
                        ))
                .thenReturn(transferenciaDTOSWithOperatorAnd2020);

    }

    @Test
    @DisplayName("return pageable of List transferencias in conta with the contaId")
    void listByContaWithContaId_ReturnsListOfTransfer_WhenSuccessful() {
        Long contaId = TransferenciaCreator.createTransferencia().getConta().getId();

        Page<TransferenciaDTO> transferencias = transferenciaController
                .listByContaId(null, contaId, LocalDate.parse("1970-01-01"), LocalDate.parse("2030-01-01"), "")
                .getBody();

        Assertions.assertThat(transferencias.toList()).isNotNull().isNotEmpty().hasSize(2);

        Assertions.assertThat(transferencias.toList().get(0).getValor()).isEqualTo(new BigDecimal("20895.46"));
        Assertions.assertThat(transferencias.toList().get(1).getValor()).isEqualTo(new BigDecimal("10895.46"));
        Assertions.assertThat(transferencias.toList().get(0).getNomeOperadorTransacao()).isEqualTo(null);
        Assertions.assertThat(transferencias.toList().get(1).getNomeOperadorTransacao()).isEqualTo("Fulano");
    }

    @Test
    @DisplayName("return pageable of transfers of conta with the contaId and the operator")
    void listByContaWithContaIdAndOperator_ReturnsListOfTransfer_WhenSuccessful() {
        Long contaId = TransferenciaCreator.createTransferencia().getConta().getId();

        Page<TransferenciaDTO> transferencias = transferenciaController
                .listByContaId(null,
                        contaId,
                        LocalDate.parse("1970-01-01"),
                        LocalDate.parse("2030-01-01"),
                        TransferenciaCreator.transferenciaDTOSWithOperatorAnd2020().getNomeOperadorTransacao())
                .getBody();

        Assertions.assertThat(transferencias.toList()).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(transferencias.toList().get(0).getValor()).isEqualTo(new BigDecimal("10895.46"));
        Assertions.assertThat(transferencias.toList().get(0).getNomeOperadorTransacao()).isEqualTo("Fulano");
    }

    @Test
    @DisplayName("return pageable of transfers of conta with the contaId and that is between dates")
    void listByContaWithContaIdAndDateRange_ReturnsListOfTransfer_WhenSuccessful() {
        Long contaId = TransferenciaCreator.createTransferencia().getConta().getId();

        Page<TransferenciaDTO> transferencias = transferenciaController
                .listByContaId(null,
                        contaId,
                        LocalDate.parse("2020-01-01"),
                        LocalDate.parse("2022-01-01"),
                        "")
                .getBody();

        Assertions.assertThat(transferencias.toList()).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(transferencias.toList().get(0).getValor()).isEqualTo(new BigDecimal("10895.46"));
        Assertions.assertThat(transferencias.toList().get(0).getDataTransferencia().toLocalDateTime())
                .isAfterOrEqualTo(formatDateToLocalDate("2020-01-01").atStartOfDay());
    }

    @Test
    @DisplayName("return pageable with empty list of transfers")
    void listByContaWithContaIdAndOperadorThatDoesNotExists_ReturnsEmptyList_WhenSuccessful() {
        Long contaId = TransferenciaCreator.createTransferencia().getConta().getId();

        Page<TransferenciaDTO> transferencias = transferenciaController
                .listByContaId(null,
                        contaId,
                        LocalDate.parse("2020-01-01"),
                        LocalDate.parse("2022-01-01"),
                        "Beltrano")
                .getBody();

        Assertions.assertThat(transferencias).isNull();
    }
}