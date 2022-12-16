package br.com.banco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transferencia {

    Long id;

    LocalDateTime transferDate;

    private BigDecimal amount;
    private String type;
    private String nameOfOperator;
    Long conta_id;

}
