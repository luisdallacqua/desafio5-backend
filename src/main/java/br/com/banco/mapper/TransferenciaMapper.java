package br.com.banco.mapper;


import br.com.banco.dto.Transferencia.TransferenciaDTO;
import br.com.banco.model.Transferencia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface TransferenciaMapper {
   TransferenciaMapper INSTANCE = Mappers.getMapper(TransferenciaMapper.class);
    TransferenciaDTO toRest(Transferencia transferencia);


    default public Page<TransferenciaDTO> toRest(Page<Transferencia> page) {
        return page.map(this::toRest);
    }
}