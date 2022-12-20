package br.com.banco.mapper;


import br.com.banco.dto.Transferencia.TransferenciaDTO;
import br.com.banco.model.Transferencia;
import br.com.banco.wrapper.PageableResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageDto;

import java.util.List;

//@Mapper(componentModel = "spring")
//public abstract class TransferenciaMapper {
//
//    public static final TransferenciaMapper INSTANCE = Mappers.getMapper(TransferenciaMapper.class);
//
//    public abstract Transferencia toTransferencia(TransferenciaDTO transferenciaDTO);
//
//    public PageDTO<TransferenciaDTO> map(Page<Transferencia> vehicles) {
//        return map(1, vehicles).getValue(); //Maybe do null checks as well
//    }
//
//   }
//}
@Mapper(componentModel = "spring")
public interface TransferenciaMapper {
    TransferenciaDTO toRest(Transferencia transferencia);

    default public Page<TransferenciaDTO> toRest(Page<Transferencia> page) {
        return page.map(this::toRest);
    }
}