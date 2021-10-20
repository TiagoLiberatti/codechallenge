package com.tiagodev.codechallenge.core.usecase.gateway;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;

import java.util.List;

public interface ContaBancariaGateway {

    ContaBancariaEntity buscarContaBancariaPorId(Long idContaBancaria);

    ContaBancariaEntity buscarContaBancariaPorNumero(String numeroContaBancaria);

    List<ContaBancariaEntity> buscarContasBancarias(String nome, String agencia, Boolean chequeEspecialLiberado);

    List<ContaBancariaEntity> salvarContasBancarias(List<ContaBancariaEntity> contasBancarias);

    ContaBancariaEntity salvarContaBancaria(ContaBancariaEntity contaBancaria);

    ContaBancariaEntity atualizarContaBancaria(ContaBancariaEntity contaBancaria);

    void deletarContaBancaria(Long idContaBancaria);

}