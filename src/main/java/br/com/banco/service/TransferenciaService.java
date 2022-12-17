package br.com.banco.service;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransferenciaService {

    private TransferenciaRepository transferenciaRepository;

    public Transferencia create(Transferencia transferencia){
        return transferenciaRepository.save(transferencia);
    }
}
