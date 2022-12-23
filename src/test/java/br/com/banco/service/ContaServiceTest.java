package br.com.banco.service;

import br.com.banco.exceptions.BadRequestException;
import br.com.banco.model.Conta;
import br.com.banco.repository.ContaRepository;
import br.com.banco.util.ContaCreator;
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

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ContaServiceTest {
    @InjectMocks
    private ContaService contaService;
    @Mock
    private ContaRepository contaRepositoryMock;
    @BeforeEach
    void setup() {
        PageImpl<Conta> contas = new PageImpl<>(List.of(ContaCreator.createConta(), ContaCreator.createConta2()));
        BDDMockito.when(contaRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(contas);

        BDDMockito.when(contaRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ContaCreator.createConta()));

        BDDMockito.when(contaRepositoryMock.findById(ArgumentMatchers.eq(2L)))
                .thenReturn(Optional.empty());
    }

    @Test
    @DisplayName("List all should return a Page List of contas")
    void list_RetornaUmaListaDeContas_QuandoBemSucedido() {
        Page<Conta> contas = contaService.listAll(PageRequest.of(0, 5));

        Assertions.assertThat(contas.toList()).isNotNull().hasSize(2);
        Assertions.assertThat(contas.toList().get(0).getId()).isEqualTo(1L);
        Assertions.assertThat(contas.toList().get(0).getNomeResponsavel()).isEqualTo("Fulano");
        Assertions.assertThat(contas.toList().get(1).getId()).isEqualTo(2L);
        Assertions.assertThat(contas.toList().get(1).getNomeResponsavel()).isEqualTo("Sicrano");
    }

    @Test
    @DisplayName("findById should return specific conta")
    void findByIdOrThrowAnException_ThrowsBadRequestException_WhenContaIsNotFound() {
        Long expectedId = ContaCreator.createConta().getId();

        Conta conta = contaService.findByIdOrThrowAnException(1L);

        Assertions.assertThat(conta.getId()).isNotNull().isEqualTo(expectedId);
    }
    @Test
    @DisplayName("findById should return specific conta")
    void findById_RetornaUmaConta_QuandoBemSucedido() {
        Conta conta = ContaCreator.createConta2();

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> contaService.findByIdOrThrowAnException(conta.getId()));
    }
}