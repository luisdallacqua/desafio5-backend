package br.com.banco.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "transferencia")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataTransferencia;

    @Column(nullable = false)
    private BigDecimal valor;
    private String tipo;
    private String nomeOperadorTransacao;

    @ManyToOne
    @JoinColumn(name="conta_id", nullable = false)
    private Conta conta;



}
