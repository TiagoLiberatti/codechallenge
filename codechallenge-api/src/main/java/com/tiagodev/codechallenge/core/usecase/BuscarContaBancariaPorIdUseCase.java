package com.tiagodev.codechallenge.core.usecase;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.core.exceptions.UseCaseException;
import com.tiagodev.codechallenge.core.usecase.gateway.ContaBancariaGateway;
import com.tiagodev.codechallenge.dataprovider.exceptions.DataProviderException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuscarContaBancariaPorIdUseCase {

    @Autowired
    private ContaBancariaGateway contaBancariaGateway;

    public ContaBancariaEntity executar(Long idContaBancaria){
        return this.buscarContaBancaria(idContaBancaria);
    }

    private ContaBancariaEntity buscarContaBancaria(Long idContaBancaria){
        try{
            return this.contaBancariaGateway.buscarContaBancariaPorId(idContaBancaria);
        }catch (DataProviderException e){
            throw new UseCaseException("Ocorreu um erro ao buscar conta bancaria por ID");
        }
    }


}