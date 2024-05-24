package com.servico.backservico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.servico.backservico.model.Servico;
import com.servico.backservico.model.enums.StatusEnum;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    @Query("SELECT s FROM Servico s where s.valorServico is null or s.valorServico <> null and s.valorServico = 0 ")
    public List<Servico> findPendingPayments();

    @Query("SELECT s FROM Servico s where s.status = 3")
    public List<Servico> findByCanceledService();

}
