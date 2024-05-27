package com.servico.backservico.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.mapping.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_servico")
@Data
public class Servico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sv_id")
    private Long id;

    @Column(name = "sv_nm_cliente")
    private String nomeCliente;

    @Column(name = "sv_dt_inicio")
    private LocalDate dataInicio;

    @Column(name = "sv_dt_fim")
    private LocalDate dataFim;

    @Column(name = "sv_dt_cancelamento")
    private LocalDate dataCancelamento;

    @Column(name = "sv_dt_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "sv_dsc_servico")
    private String descricaoServico;

    @Column(name = "sv_vl_servico")
    private Double valorServico;

    @Column(name = "sv_vl_pago")
    private Double valorPago;

    @Column(name = "sv_status_id")
    @Enumerated(value = EnumType.ORDINAL)
    private Long status;

}
