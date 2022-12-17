package br.com.banco.dto.Conta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaPostDTO {

    @NotEmpty(message = "The anime name cannot be empty ")
    private  String nomeResponsavel;
}
