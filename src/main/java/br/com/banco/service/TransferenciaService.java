package br.com.banco.service;

import br.com.banco.exceptions.BadRequestException;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final ContaRepository contaRepository;

    public Transferencia create(Transferencia transferencia){
        return transferenciaRepository.save(transferencia);
    }

    public List<Transferencia> listAll() {
        return transferenciaRepository.findAll();
    }

    public List<Transferencia> listById(Long id) {
        contaRepository.findById(id).orElseThrow(() -> new BadRequestException("Não existe usuário com esse id"));
        return transferenciaRepository.findTransferenciasByConta_Id(id);
    }

    public List<Transferencia> listById(Long id, String operador) {
        contaRepository.findById(id).orElseThrow(() -> new BadRequestException("Não existe usuário com esse id"));
        return transferenciaRepository.findTransferenciasByConta_IdAndNomeOperadorTransacao(id, operador);
    }

    public List<Transferencia> listById(Long id, String operador, LocalDate dataInicio, LocalDate dataFim) {
        contaRepository.findById(id).orElseThrow(() -> new BadRequestException("Não existe usuário com esse id"));
        return transferenciaRepository
                .findTransferenciasByConta_IdAndNomeOperadorTransacaoAndDataTransferenciaBetween(
                        id,
                        operador,
                        dataInicio.atStartOfDay(ZoneId.systemDefault()),
                        dataFim.atStartOfDay(ZoneId.systemDefault()));
    }

    public List<Transferencia> listById(Long id, LocalDate dataInicio, LocalDate dataFim) {
        contaRepository.findById(id).orElseThrow(() -> new BadRequestException("Não existe usuário com esse id"));
        return transferenciaRepository
                .findTransferenciasByConta_IdAndDataTransferenciaBetween(
                        id,
                        dataInicio.atStartOfDay(ZoneId.systemDefault()),
                        dataFim.atStartOfDay(ZoneId.systemDefault()));
    }
}
