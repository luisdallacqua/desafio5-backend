package br.com.banco.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "transferencia")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Data de transferência não pode ser Nulo")
    @Schema(description = "This is the date transfer field", example = "2020-12-31", required = true)
    private ZonedDateTime dataTransferencia;
    @Column(nullable = false)
    @Digits(integer = 10, fraction = 2)
    @Schema(description = "This is the amount of transfer", example = "2000.02", required = true)
    private BigDecimal valor;

    @NotNull(message = "Tipo da transção não pode ser nulo")
    @Schema(description = "This is the type of transfer, that can be: TRANFERENCIA, DEPOSITO, SAQUE",
            example = "TRANSFERENCIA",
            required = true)
    private String tipo;

    @Schema(description = "This is the name of operator",
            example = "Beltrano")
    private String nomeOperadorTransacao;

    @ManyToOne
    @NotNull(message = "Conta da transação não pode ser nulo")
    @JoinColumn(name = "conta_id", referencedColumnName = "id_conta")
    private Conta conta;

}
