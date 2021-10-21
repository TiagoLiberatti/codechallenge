package com.tiagodev.codechallenge.core.usecase;

import com.tiagodev.codechallenge.core.exceptions.UseCaseException;
import com.tiagodev.codechallenge.core.usecase.gateway.ContaBancariaGateway;
import com.tiagodev.codechallenge.dataprovider.exceptions.DataProviderException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.tiagodev.codechallenge.utils.MessageExceptionUtil.ERRO_DELETAR_CONTA_BANCARIA_USE_CASE;

@Component
public class DeletarContaBancariaUseCase {

    @Autowired
    private ContaBancariaGateway contaBancariaGateway;

    public void executar(Long idContaBancaria){
        this.salvarContaBancariaUseCase(idContaBancaria);
    }

    private void salvarContaBancariaUseCase(Long idContaBancaria){
        try{
            this.contaBancariaGateway.deletarContaBancaria(idContaBancaria);
        }catch (DataProviderException e){
            throw new UseCaseException(ERRO_DELETAR_CONTA_BANCARIA_USE_CASE);
        }
    }

}