package com.tiagodev.codechallenge.entrypoint.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContaBancariaHttpResponse {

    private Long id;

    private String nome;

    private String numeroConta;

    private String agencia;

    private Boolean chequeEspecialLiberado;

    private BigDecimal saldo;

    private BigDecimal chequeEspecial;

    private Double taxa;

}