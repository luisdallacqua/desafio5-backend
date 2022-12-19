package br.com.banco.service;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    public Transferencia create(Transferencia transferencia){
        return transferenciaRepository.save(transferencia);
    }

    public List<Transferencia> listAll() {
        return transferenciaRepository.findAll();
    }

    public List<Transferencia> listById(Long id) {
        return transferenciaRepository.findTransferenciasByConta_Id(id);
    }

    public List<Transferencia> listById(Long id, String operador) {
        return transferenciaRepository.findTransferenciasByConta_IdAndNomeOperadorTransacao(id, operador);
    }

    public List<Transferencia> listById(Long id, String operador, LocalDate dataInicio, LocalDate dataFim) {
        return transferenciaRepository
                .findTransferenciasByConta_IdAndNomeOperadorTransacaoAndDataTransferenciaBetween(
                        id,
                        operador,
                        dataInicio.atStartOfDay(ZoneId.systemDefault()),
                        dataFim.atStartOfDay(ZoneId.systemDefault()));
    }

    public List<Transferencia> listById(Long id, LocalDate dataInicio, LocalDate dataFim) {
        return transferenciaRepository
                .findTransferenciasByConta_IdAndDataTransferenciaBetween(
                        id,
                        dataInicio.atStartOfDay(ZoneId.systemDefault()),
                        dataFim.atStartOfDay(ZoneId.systemDefault()));
    }
}
