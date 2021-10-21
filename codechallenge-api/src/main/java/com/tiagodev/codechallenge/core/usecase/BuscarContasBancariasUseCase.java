package com.tiagodev.codechallenge.core.usecase;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.core.exceptions.UseCaseException;
import com.tiagodev.codechallenge.core.usecase.gateway.ContaBancariaGateway;
import com.tiagodev.codechallenge.dataprovider.exceptions.DataProviderException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.tiagodev.codechallenge.utils.MessageExceptionUtil.ERRO_BUSCAR_LISTA_CONTAS_BANCARIAS_USE_CASE;

@Component
public class BuscarContasBancariasUseCase {

    @Autowired
    private ContaBancariaGateway contaBancariaGateway;

    public List<ContaBancariaEntity> executar(String nome, String agencia, Boolean chequeEspecialLiberado){
        return this.buscarContasBancarias(nome, agencia, chequeEspecialLiberado);
    }

    private List<ContaBancariaEntity> buscarContasBancarias(String nome, String agencia, Boolean chequeEspecialLiberado){
        try{
            return this.contaBancariaGateway.buscarContasBancarias(nome, agencia, chequeEspecialLiberado);
        }catch (DataProviderException e){
            throw new UseCaseException(ERRO_BUSCAR_LISTA_CONTAS_BANCARIAS_USE_CASE);
        }
    }

}