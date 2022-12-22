package br.com.banco.model;

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

    @NotNull(message="Data de transferência não pode ser Nulo")
    private ZonedDateTime dataTransferencia;
    @Column(nullable = false)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valor;

    @NotNull(message="Tipo da transção não pode ser nulo")
    private String tipo;
    private String nomeOperadorTransacao;

    @ManyToOne
    @NotNull(message="Conta da transação não pode ser nulo")
    @JoinColumn(name="conta_id", referencedColumnName ="id_conta")
    private Conta conta;

}
