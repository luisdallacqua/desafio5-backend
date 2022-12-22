package br.com.banco.service;

import br.com.banco.dto.Transferencia.TransferenciaDTO;
import br.com.banco.exceptions.BadRequestException;
import br.com.banco.mapper.TransferenciaMapper;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@RequiredArgsConstructor
@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final ContaRepository contaRepository;

    public Transferencia create(Transferencia transferencia) {
        return transferenciaRepository.save(transferencia);
    }

    public Page<TransferenciaDTO> listAll(Pageable pageable) {
        return TransferenciaMapper.INSTANCE.toRest(transferenciaRepository.findAll(pageable));
    }

    public Page<TransferenciaDTO> listByContaId(Pageable pageable, Long id) {
        contaRepository.findById(id).orElseThrow(() -> new BadRequestException("Não existe usuário com esse id"));
        return TransferenciaMapper.INSTANCE.toRest(transferenciaRepository.findTransferenciasByConta_Id(pageable, id));
    }

    public Page<TransferenciaDTO> listTransferenciasByConta_IdAndNomeOperadorTransacao(
            Pageable pageable,
            Long id,
            String operador
    ) {
        contaRepository.findById(id).orElseThrow(() -> new BadRequestException("Não existe usuário com esse id"));
        return TransferenciaMapper.INSTANCE.toRest(
                transferenciaRepository.findTransferenciasByConta_IdAndNomeOperadorTransacao(pageable, id, operador));
    }

    public Page<TransferenciaDTO> listTransferenciasByConta_IdAndDataTransferenciaBetween(
            Pageable pageable,
            Long id,
            LocalDate dataInicio,
            LocalDate dataFim
    ) {
        contaRepository.findById(id).orElseThrow(() -> new BadRequestException("Não existe usuário com esse id"));

        return TransferenciaMapper.INSTANCE.toRest(transferenciaRepository
                .findTransferenciasByConta_IdAndDataTransferenciaBetween(
                        pageable,
                        id,
                        dataInicio.atStartOfDay(ZoneId.systemDefault()),
                        dataFim.atStartOfDay(ZoneId.systemDefault())));
    }

    public Page<TransferenciaDTO> listTransferenciasByConta_IdAndNomeOperadorTransacaoAndDataTransferenciaBetween(
            Pageable pageable,
            Long id,
            String operador,
            LocalDate dataInicio,
            LocalDate dataFim
    ) {
        contaRepository.findById(id).orElseThrow(() -> new BadRequestException("Não existe usuário com esse id"));
        return TransferenciaMapper.INSTANCE.toRest(
                transferenciaRepository.findTransferenciasByConta_IdAndNomeOperadorTransacaoAndDataTransferenciaBetween(
                        pageable,
                        id,
                        operador,
                        dataInicio.atStartOfDay(ZoneId.systemDefault()),
                        dataFim.atStartOfDay(ZoneId.systemDefault())
                        ));
    }
}
