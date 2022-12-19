package br.com.banco.mapper;

import br.com.banco.dto.Conta.ContaDTO;
import br.com.banco.model.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ContaMapper {
    public static final ContaMapper INSTANCE = Mappers.getMapper(ContaMapper.class);

    public abstract Conta toConta(ContaDTO contaDTO);
    public abstract ContaDTO postDTO(Conta conta);

}
