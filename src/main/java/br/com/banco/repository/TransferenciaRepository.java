package br.com.banco.repository;

import br.com.banco.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    public List<Transferencia> findTransferenciasByConta_Id(Long id);
    public List<Transferencia> findTransferenciasByConta_IdAndNomeOperadorTransacao(Long id, String name);

    public List<Transferencia> findTransferenciasByConta_IdAndDataTransferenciaBetween(
            Long id,
            ZonedDateTime beginDate,
            ZonedDateTime endDate);

    public List<Transferencia> findTransferenciasByConta_IdAndNomeOperadorTransacaoAndDataTransferenciaBetween(
            Long id,
            String name,
            ZonedDateTime beginDate,
            ZonedDateTime endDate
    );
}
