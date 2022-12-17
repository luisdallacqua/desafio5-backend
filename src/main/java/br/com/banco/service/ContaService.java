package br.com.banco.service;

import br.com.banco.dto.ContaDTO;
import br.com.banco.mapper.ContaMapper;
import br.com.banco.model.Conta;
import br.com.banco.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;

    public Conta save(ContaDTO contaDTO){
        return contaRepository.save(ContaMapper.INSTANCE.toConta(contaDTO));
    }

    public Page<Conta> listAll(Pageable pageable) {
        return  contaRepository.findAll(pageable);
    }


}
