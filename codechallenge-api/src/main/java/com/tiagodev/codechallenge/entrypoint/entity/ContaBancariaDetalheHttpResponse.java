package com.tiagodev.codechallenge.entrypoint.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContaBancariaDetalheHttpResponse {

    private String nome;

    private String agenciaNumero;

    private String saldo;

    private String chequeEspecialLiberado;

    private String valorChequeEspecialDiaSeguinte;

}