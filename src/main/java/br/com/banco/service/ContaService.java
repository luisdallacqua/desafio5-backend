package br.com.banco.service;

import br.com.banco.dto.Conta.ContaPostDTO;
import br.com.banco.dto.Conta.ContaPutDTO;
import br.com.banco.mapper.ContaMapper;
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

    public Conta save(ContaPostDTO contaPostDTO){
        return contaRepository.save(ContaMapper.INSTANCE.toConta(contaPostDTO));
    }

    public Page<Conta> listAll(Pageable pageable) {
        return  contaRepository.findAll(pageable);
    }

    public Conta findByIdOrThrowAnException(long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void delete(long id) {
        contaRepository.delete(findByIdOrThrowAnException(id));
    }


    public void replace(ContaPutDTO contaPutDTO) {
        Conta savedConta = findByIdOrThrowAnException(contaPutDTO.getId());
        Conta conta = ContaMapper.INSTANCE.toConta(contaPutDTO);
        conta.setId(savedConta.getId());
        contaRepository.save(conta);
    }
}
