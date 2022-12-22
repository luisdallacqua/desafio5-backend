package br.com.banco.util;

import br.com.banco.model.Conta;

public class ContaCreator {

    public static Conta createConta(){
        return Conta.builder()
                .nomeResponsavel("Fulano")
                .id(1L)
                .build();
    }
    public static Conta createConta2(){
        return Conta.builder()
                .nomeResponsavel("Sicrano")
                .id(2L)
                .build();
    }
    public static Conta createContaInexistente(){
        return Conta.builder()
                .nomeResponsavel("Inexistente")
                .id(3L)
                .build();
    }
}
