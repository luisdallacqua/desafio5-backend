package br.com.banco.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class NotFoundException extends ExceptionDetails {
}
