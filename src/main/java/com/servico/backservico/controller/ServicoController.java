package com.servico.backservico.controller;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.servico.backservico.model.Servico;
import com.servico.backservico.service.ServicoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestScope
@RequestMapping("/api/servico")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/")
    public List<Servico> findByAll() {
        return servicoService.findAll();
    }

    @GetMapping("/pending-payments")
    public List<Servico> findPendingPayments() throws ObjectNotFoundException {
        return servicoService.findPendingPayments();
    }

    @GetMapping("/canceled")
    public List<Servico> findByCanceledService() throws ObjectNotFoundException {
        return servicoService.findByCanceledService();
    }

    @PostMapping("/create")
    public Servico insert(@RequestBody Servico servico) {
        return servicoService.insert(servico);
    }

    @PutMapping("/update")
    public Servico update(@RequestBody Servico servico) throws ObjectNotFoundException {
        return servicoService.update(servico);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        servicoService.delete(id);
    }

}
