package com.tiagodev.codechallenge.core.usecase;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.core.exceptions.UseCaseException;
import com.tiagodev.codechallenge.core.usecase.gateway.ContaBancariaGateway;
import com.tiagodev.codechallenge.dataprovider.exceptions.DataProviderException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.tiagodev.codechallenge.utils.MessageExceptionUtil.ERRO_SALVAR_CONTA_BANCARIA_USE_CASE;

@Component
public class SalvarContaBancariaUseCase {

    @Autowired
    private ContaBancariaGateway contaBancariaGateway;

    public ContaBancariaEntity executar(ContaBancariaEntity contaBancariaEntityList){
        return this.salvarContaBancariaUseCase(contaBancariaEntityList);
    }

    private ContaBancariaEntity salvarContaBancariaUseCase(ContaBancariaEntity contaBancariaEntity){
        try{
            return this.contaBancariaGateway.salvarContaBancaria(contaBancariaEntity);
        }catch (DataProviderException e){
            throw new UseCaseException(ERRO_SALVAR_CONTA_BANCARIA_USE_CASE);
        }
    }

}