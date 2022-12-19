package br.com.banco.dto.Transferencia;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class TransferenciaDTO {
    private Long id;

    @NotNull(message="Start Date cannot be null")
    private ZonedDateTime dataTransferencia;
    @Column(nullable = false)
    private BigDecimal valor;
    private String tipo;
    private String nomeOperadorTransacao;
    private Long conta_id;
}
