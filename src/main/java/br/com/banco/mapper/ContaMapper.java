package br.com.banco.mapper;

import br.com.banco.dto.ContaDTO;
import br.com.banco.model.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ContaMapper {
    public static ContaMapper INSTANCE = Mappers.getMapper(ContaMapper.class);

    public abstract Conta toConta(ContaDTO contaDTO);
}
