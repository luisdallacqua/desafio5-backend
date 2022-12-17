package br.com.banco.controller;

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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class ContaControllerTest {

    Conta contaValida1 = new Conta(1L, "Usuário 1");
    Conta contaValida2 = new Conta(2L, "Usuário 2");

    @InjectMocks
    private ContaController contaController;

    @Mock
    private ContaService contaServiceMock;

    @BeforeEach
    void setup(){
        List<Conta> contas = new ArrayList<>(List.of(contaValida1, contaValida2));
        BDDMockito.when(contaServiceMock.listAll())
                .thenReturn(contas);

        BDDMockito.when(contaServiceMock.save(ArgumentMatchers.any(Conta.class)))
                .thenReturn(contaValida1);

    }

    @Test
    @DisplayName("List all deve retornar uma lista de contas quando bem sucedido")
    void listAll_RetornaUmaListaDeContas_QuandoBemSucedido(){
        List<Conta> contas = contaController.list().getBody();

        Assertions.assertThat(contas).isNotNull().hasSize(2);
        Assertions.assertThat(contas.get(0).getIdConta()).isEqualTo(1L);
        Assertions.assertThat(contas.get(0).getNomeResponsavel()).isEqualTo("Usuário 1");
        Assertions.assertThat(contas.get(1).getIdConta()).isEqualTo(2L);
        Assertions.assertThat(contas.get(1).getNomeResponsavel()).isEqualTo("Usuário 2");
    }

    @Test
    @DisplayName("save retorna uma conta quando bem sucedido")
    void save_RetornaUmaConta_QuandoBemSucedido(){
        Conta conta = contaController.save(new Conta(1L, "Usuário 1")).getBody();

        Assertions.assertThat(conta).isNotNull().isEqualTo(contaValida1);

    }

}