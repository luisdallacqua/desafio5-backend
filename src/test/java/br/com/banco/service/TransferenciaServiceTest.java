package br.com.banco.service;

import br.com.banco.dto.Transferencia.TransferenciaDTO;
import br.com.banco.exceptions.BadRequestException;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.util.ContaCreator;
import br.com.banco.util.DateUtilTest;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class TransferenciaServiceTest {

    @InjectMocks
    private TransferenciaService transferenciaService;
    @Mock
    private TransferenciaRepository transferenciaRepositoryMock;
    @Mock
    private ContaRepository contaRepositoryMock;

    @BeforeEach
    void setup() {
        Conta conta = ContaCreator.createConta();

        PageImpl<Transferencia> transferencias = new PageImpl<>(
                List.of(TransferenciaCreator.createTransferencia(),
                        TransferenciaCreator.createTransferenciaWithOperator()));

        PageImpl<Transferencia> transferenciaSWithOperatorAnd2020 = new PageImpl<>(
                List.of(TransferenciaCreator.createTransferenciaWithOperator()));

        BDDMockito.when(contaRepositoryMock.findById(ArgumentMatchers.eq(1L)))
                .thenReturn(Optional.of(conta));

        BDDMockito.when(contaRepositoryMock.findById(ArgumentMatchers.eq(3L)))
                .thenReturn(Optional.empty());

        BDDMockito.when(transferenciaRepositoryMock.save(ArgumentMatchers.any(Transferencia.class)))
                .thenReturn(TransferenciaCreator.createTransferencia());

        BDDMockito.when(transferenciaRepositoryMock.findTransferenciasByConta_Id(
                        ArgumentMatchers.any(PageRequest.class),
                        ArgumentMatchers.eq(1L)))
                .thenReturn(transferencias);

        BDDMockito.when(transferenciaRepositoryMock.findTransferenciasByConta_IdAndNomeOperadorTransacao(
                        ArgumentMatchers.any(PageRequest.class),
                        ArgumentMatchers.anyLong(),
                        ArgumentMatchers.eq("Fulano")))
                .thenReturn(transferenciaSWithOperatorAnd2020);

        BDDMockito.given(transferenciaRepositoryMock.findTransferenciasByConta_IdAndDataTransferenciaBetween(
                        ArgumentMatchers.any(PageRequest.class),
                        ArgumentMatchers.anyLong(),
                        ArgumentMatchers.eq(DateUtilTest.DATE_2019),
                        ArgumentMatchers.eq(DateUtilTest.DATE_2020)))
                .willReturn(transferenciaSWithOperatorAnd2020);

        BDDMockito.given(transferenciaRepositoryMock.findTransferenciasByConta_IdAndNomeOperadorTransacaoAndDataTransferenciaBetween(
                        ArgumentMatchers.any(PageRequest.class),
                        ArgumentMatchers.anyLong(),
                        ArgumentMatchers.eq(DateUtilTest.DATE_2019),
                        ArgumentMatchers.eq(DateUtilTest.DATE_2020),
                        ArgumentMatchers.eq("Fulano")))
                .willReturn(transferenciaSWithOperatorAnd2020);

    }

    @Test
    @DisplayName("return pageable of List transferencias based in contaId")
    void listByContaWithContaId_ReturnsListOfTransfer_WhenSuccessful() {
        Conta conta = ContaCreator.createConta();

        Page<TransferenciaDTO> transferencias = transferenciaService
                .listByContaId(PageRequest.of(0, 5), conta.getId());

        Assertions.assertThat(transferencias.toList()).isNotNull().isNotEmpty().hasSize(2);

        Assertions.assertThat(transferencias.toList().get(0).getValor()).isEqualTo(new BigDecimal("40895.46"));
        Assertions.assertThat(transferencias.toList().get(1).getValor()).isEqualTo(new BigDecimal("30895.46"));
        Assertions.assertThat(transferencias.toList().get(0).getNomeOperadorTransacao()).isEqualTo(null);
        Assertions.assertThat(transferencias.toList().get(1).getNomeOperadorTransacao()).isEqualTo("Beltrano");
    }

    @Test
    @DisplayName("return pageable of List transferencias based in contaId and the operador")
    void listByContaWithContaIdAndOperator_ReturnsListOfTransfer_WhenSuccessful() {
        Conta conta = ContaCreator.createConta();

        Page<TransferenciaDTO> transferencias = transferenciaService
                .listTransferenciasByConta_IdAndNomeOperadorTransacao(
                        PageRequest.of(0, 5),
                        conta.getId(),
                        TransferenciaCreator.transferenciaDTOSWithOperatorAnd2020().getNomeOperadorTransacao());

        Assertions.assertThat(transferencias.toList()).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(transferencias.toList().get(0).getValor()).isEqualTo(new BigDecimal("30895.46"));
        Assertions.assertThat(transferencias.toList().get(0).getNomeOperadorTransacao()).isEqualTo("Beltrano");
    }

    @Test
    @DisplayName("return pageable of List transferencias based in contaId and date range")
    void listByContaWithContaIdAndDataTransferenciaBetween_ReturnsListOfTransfer_WhenSuccessful() {
        Conta conta = ContaCreator.createConta();

        Page<TransferenciaDTO> transferencias = transferenciaService
                .listTransferenciasByConta_IdAndDataTransferenciaBetween(
                        PageRequest.of(0, 5),
                        conta.getId(),
                        LocalDate.parse("2019-01-01"),
                        LocalDate.parse("2020-01-01"));

        Assertions.assertThat(transferencias.toList()).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(transferencias.toList().get(0).getValor()).isEqualTo(new BigDecimal("30895.46"));
        Assertions.assertThat(transferencias.toList().get(0).getNomeOperadorTransacao()).isEqualTo("Beltrano");
    }
    @Test
    @DisplayName("return pageable of List transferencias based in contaId, date range and operador")
    void listByContaWithContaIdAndNomeOperadorTransacaoAndDataTransferenciaBetween_ReturnsListOfTransfer_WhenSuccessful() {
        Conta conta = ContaCreator.createConta();

        Page<TransferenciaDTO> transferencias = transferenciaService
                .listTransferenciasByConta_IdAndNomeOperadorTransacaoAndDataTransferenciaBetween(
                        PageRequest.of(0, 5),
                        conta.getId(),
                        LocalDate.parse("2019-01-01"),
                        LocalDate.parse("2020-01-01"),
                        TransferenciaCreator.transferenciaDTOSWithOperatorAnd2020().getNomeOperadorTransacao());

        Assertions.assertThat(transferencias.toList()).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(transferencias.toList().get(0).getValor()).isEqualTo(new BigDecimal("30895.46"));
        Assertions.assertThat(transferencias.toList().get(0).getNomeOperadorTransacao()).isEqualTo("Beltrano");
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException throws BadRequestException when conta is not found")
    void findByIdOrThrowBadRequestException_ThrowsBadRequestException_WhenAnimeIsNotFound(){
        Conta conta = ContaCreator.createContaInexistente();

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> transferenciaService.listByContaId(null , conta.getId()));
    }


}