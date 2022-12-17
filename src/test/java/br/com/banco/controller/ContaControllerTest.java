package br.com.banco.controller;

import br.com.banco.dto.ContaDTO;
import br.com.banco.model.Conta;
import br.com.banco.service.ContaService;
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

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class ContaControllerTest {

    Conta contaValida1 = Conta.builder()
            .nomeResponsavel("Usuário 1")
            .idConta(1L)
            .build();
    Conta contaValida2 = Conta.builder()
            .nomeResponsavel("Usuário 2")
            .idConta(2L)
            .build();
    ContaDTO contaParaSerSalva = ContaDTO.builder()
            .nomeResponsavel("Usuário 1")
            .build();

    @InjectMocks
    private ContaController contaController;

    @Mock
    private ContaService contaServiceMock;

    @BeforeEach
    void setup() {
        PageImpl<Conta> contas = new PageImpl<>(List.of(contaValida1, contaValida2));
        BDDMockito.when(contaServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(contas);

        BDDMockito.when(contaServiceMock.save(ArgumentMatchers.any(ContaDTO.class)))
                .thenReturn(contaValida1);

    }

    @Test
    @DisplayName("List all deve retornar uma lista de contas quando bem sucedido")
    void listAll_RetornaUmaListaDeContas_QuandoBemSucedido() {
        Page<Conta> contas = contaController.list(null).getBody();

        Assertions.assertThat(contas.toList()).isNotNull().hasSize(2);
        Assertions.assertThat(contas.toList().get(0).getIdConta()).isEqualTo(1L);
        Assertions.assertThat(contas.toList().get(0).getNomeResponsavel()).isEqualTo("Usuário 1");
        Assertions.assertThat(contas.toList().get(1).getIdConta()).isEqualTo(2L);
        Assertions.assertThat(contas.toList().get(1).getNomeResponsavel()).isEqualTo("Usuário 2");
    }

    @Test
    @DisplayName("save retorna uma conta quando bem sucedido")
    void save_RetornaUmaConta_QuandoBemSucedido() {
        Conta conta = contaController.save(contaParaSerSalva).getBody();

        Assertions.assertThat(conta).isNotNull().isEqualTo(contaValida1);
    }

}