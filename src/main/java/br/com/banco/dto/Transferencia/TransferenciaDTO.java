package br.com.banco.dto.Transferencia;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
public class TransferenciaDTO {
    private Long id;
    @NotNull(message="Data de transferência não pode ser Nulo")
    private ZonedDateTime dataTransferencia;
    @Column(nullable = false)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valor;

    @NotNull(message="Tipo da transção não pode ser nulo")
    @Size(max = 15, message = "O tamanho máximo do tipo da transação é 15 caracteres")
    private String tipo;
    private String nomeOperadorTransacao;
}
