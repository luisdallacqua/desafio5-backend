package br.com.banco.model;

import br.com.banco.dto.Transferencia.TransferenciaDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
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
    @JoinColumn(name="conta_id", referencedColumnName ="id_conta")
    private Conta conta;

}
