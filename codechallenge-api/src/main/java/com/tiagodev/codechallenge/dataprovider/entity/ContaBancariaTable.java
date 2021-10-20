package com.tiagodev.codechallenge.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "conta_bancaria")
public class ContaBancariaTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "numero_conta")
    private String numeroConta;

    @Column(name = "agencia")
    private String agencia;

    @Column(name = "cheque_especial_liberado")
    private Boolean chequeEspecialLiberado;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "cheque_especial")
    private BigDecimal chequeEspecial;

    @Column(name = "taxa")
    private Double taxa;

}