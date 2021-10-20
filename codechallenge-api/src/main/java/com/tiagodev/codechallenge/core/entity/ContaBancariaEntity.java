package com.tiagodev.codechallenge.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContaBancariaEntity {

    private Long id;

    private String nome;

    private String numeroConta;

    private String agencia;

    private Boolean chequeEspecialLiberado;

    private BigDecimal saldo;

    private BigDecimal chequeEspecial;

    private Double taxa;

}