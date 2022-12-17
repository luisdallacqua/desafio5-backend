package br.com.banco.service;

import br.com.banco.model.Conta;
import br.com.banco.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;

    public Conta save(Conta conta){
        return contaRepository.save(conta);
    }

    public List<Conta> listAll() {
        return  contaRepository.findAll();
    }


}
