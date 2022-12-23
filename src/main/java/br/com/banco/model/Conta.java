package br.com.banco.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "conta")
@Builder
public class Conta{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Long id;
    @NotEmpty(message = "O nome do responsável não pode ser vazio")
    @Schema(description = "This is the name of count responsible",
            example = "Beltrano")
    private String nomeResponsavel;
}
