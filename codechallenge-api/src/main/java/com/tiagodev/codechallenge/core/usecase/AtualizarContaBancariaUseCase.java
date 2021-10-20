package com.tiagodev.codechallenge.core.usecase;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.core.exceptions.UseCaseException;
import com.tiagodev.codechallenge.core.usecase.gateway.ContaBancariaGateway;
import com.tiagodev.codechallenge.dataprovider.exceptions.DataProviderException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtualizarContaBancariaUseCase {

    @Autowired
    private ContaBancariaGateway contaBancariaGateway;

    public ContaBancariaEntity executar(ContaBancariaEntity contaBancariaEntityList, Long idContaBancaria){
        return this.atualizarContaBancariaUseCase(contaBancariaEntityList, idContaBancaria);
    }

    private ContaBancariaEntity atualizarContaBancariaUseCase(ContaBancariaEntity contaBancariaEntity, Long idContaBancaria){
        try{
            ContaBancariaEntity contaBancaria = this.contaBancariaGateway.buscarContaBancariaPorId(idContaBancaria);
            contaBancariaEntity.setId(contaBancaria.getId());
            return this.contaBancariaGateway.atualizarContaBancaria(contaBancariaEntity);
        }catch (DataProviderException e){
            throw new UseCaseException("Ocorreu um erro ao atualizar conta bancaria");
        }
    }

}