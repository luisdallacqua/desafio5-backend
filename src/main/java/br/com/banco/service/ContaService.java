package br.com.banco.service;

import br.com.banco.model.Conta;
import br.com.banco.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;
    public Page<Conta> listAll(Pageable pageable) {
        return  contaRepository.findAll(pageable);
    }

    public Conta findByIdOrThrowAnException(long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
