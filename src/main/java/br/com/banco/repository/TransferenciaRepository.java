package br.com.banco.repository;

import br.com.banco.model.Transferencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    Page<Transferencia> findTransferenciasByConta_Id(Pageable pageable, Long id);

    Page<Transferencia> findTransferenciasByConta_IdAndNomeOperadorTransacao(Pageable pageable, Long id, String name);

    Page<Transferencia> findTransferenciasByConta_IdAndDataTransferenciaBetween(
            Pageable pageable,
            Long id,
            ZonedDateTime beginDate,
            ZonedDateTime endDate);

    Page<Transferencia> findTransferenciasByConta_IdAndNomeOperadorTransacaoAndDataTransferenciaBetween(
            Pageable pageable,
            Long id,
            ZonedDateTime beginDate,
            ZonedDateTime endDate,
            String name
    );
}
