package com.tiagodev.codechallenge.core.usecase;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.core.exceptions.UseCaseException;
import com.tiagodev.codechallenge.core.usecase.gateway.ContaBancariaGateway;
import com.tiagodev.codechallenge.dataprovider.exceptions.DataProviderException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalvarContasBancariasUseCase {

    @Autowired
    private ContaBancariaGateway contaBancariaGateway;

    public List<ContaBancariaEntity> executar(List<ContaBancariaEntity> contaBancariaEntityList){
        return this.salvarContaBancariaUseCase(contaBancariaEntityList);
    }

    private List<ContaBancariaEntity> salvarContaBancariaUseCase(List<ContaBancariaEntity> contaBancariaEntityList){
        try{
            return this.contaBancariaGateway.salvarContasBancarias(contaBancariaEntityList);
        }catch (DataProviderException e){
            throw new UseCaseException("Ocorreu um erro ao salvar lista de contas bancarias");
        }
    }

}