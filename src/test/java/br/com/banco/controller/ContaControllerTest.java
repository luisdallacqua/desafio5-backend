package br.com.banco.controller;

import br.com.banco.model.Conta;
import br.com.banco.service.ContaService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class ContaControllerTest {
    @InjectMocks
    private ContaController contaController;

    @Mock
    private ContaService contaServiceMock;

    @BeforeEach
    void setup() {
        PageImpl<Conta> contas = new PageImpl<>(List.of(ContaCreator.createConta(), ContaCreator.createConta2()));
        BDDMockito.when(contaServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(contas);

        BDDMockito.when(contaServiceMock.findByIdOrThrowAnException(1L))
                .thenReturn(ContaCreator.createConta());
    }

    @Test
    @DisplayName("List all deve retornar uma lista de contas quando bem sucedido")
    void list_RetornaUmaListaDeContas_QuandoBemSucedido() {
        Page<Conta> contas = contaController.list(null).getBody();

        Assertions.assertThat(contas.toList()).isNotNull().hasSize(2);
        Assertions.assertThat(contas.toList().get(0).getId()).isEqualTo(1L);
        Assertions.assertThat(contas.toList().get(0).getNomeResponsavel()).isEqualTo("Usuário 1");
        Assertions.assertThat(contas.toList().get(1).getId()).isEqualTo(2L);
        Assertions.assertThat(contas.toList().get(1).getNomeResponsavel()).isEqualTo("Usuário 2");
    }

    @Test
    @DisplayName("findById retorna uma conta específica quando bem sucedido")
    void findById_RetornaUmaConta_QuandoBemSucedido() {
        Long expectedId = ContaCreator.createConta().getId();

        Conta conta = contaController.findById(1L).getBody();

        Assertions.assertThat(conta.getId()).isNotNull().isEqualTo(expectedId);
    }


}