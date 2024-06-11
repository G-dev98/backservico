package com.servico.backservico.service;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servico.backservico.model.Servico;
import com.servico.backservico.model.enums.StatusEnum;
import com.servico.backservico.repository.ServicoRepository;

import ch.qos.logback.core.status.Status;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> findAll() {
        return servicoRepository.findAll();
    }

    //PERCISTÊNCIAS

    public Servico insert(Servico servico) {
        servico.setStatus(verificaStatus(servico));
        Servico servicoBanco = servicoRepository.saveAndFlush(servico);
        return servicoBanco;
    }

    public Servico update(Servico servico) throws ObjectNotFoundException {
        servico.setStatus(verificaStatus(servico));
        Servico servicoBanco = servicoRepository.saveAndFlush(servico);
        return servicoBanco;
    }

    public void delete(Long id) {
        Servico servico = servicoRepository.findById(id).get();
        servicoRepository.delete(servico);
    }

    public Servico cancelar(Servico servico) throws ObjectNotFoundException {
        Servico servicoBanco = servicoRepository.saveAndFlush(servico);
        return servicoBanco;
    }

    // LISTAGENS

    public List<Servico> findPendingPayments() throws ObjectNotFoundException {
        return servicoRepository.findPendingPayments();
    }

    public List<Servico> findByCanceledService() throws ObjectNotFoundException {
        return servicoRepository.findByCanceledService();
    }

    // REGRAS
    private Long verificaStatus(Servico servico) {

        if (servico.getValorPago() != null && servico.getValorPago() > 0
                && servico.getDataPagamento() != null) {
            return StatusEnum.REALIZADO.id.longValue();
        } else if (servico.getDataCancelamento() != null) {
            return StatusEnum.CANCELADO.id.longValue();
        } else {
            return StatusEnum.PENDENTE.id.longValue();
        }

    }

}
