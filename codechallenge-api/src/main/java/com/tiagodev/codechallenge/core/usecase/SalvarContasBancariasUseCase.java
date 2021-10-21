package com.tiagodev.codechallenge.core.usecase;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.core.exceptions.UseCaseException;
import com.tiagodev.codechallenge.core.usecase.gateway.ContaBancariaGateway;
import com.tiagodev.codechallenge.core.utils.ConverterPlanilhaUtil;
import com.tiagodev.codechallenge.dataprovider.exceptions.DataProviderException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.tiagodev.codechallenge.utils.MessageExceptionUtil.ERRO_SALVAR_LISTA_CONTAS_BANCARIAS_USE_CASE;

@Component
public class SalvarContasBancariasUseCase {

    @Autowired
    private ContaBancariaGateway contaBancariaGateway;

    @Autowired
    private ConverterPlanilhaUtil converterArquivoPlanilhaUtil;

    public List<ContaBancariaEntity> executar() throws IOException {
        List<ContaBancariaEntity> contaBancariaEntityList = this.converterArquivoPlanilhaUtil.executar();
        return  this.salvarContaBancariaUseCase(contaBancariaEntityList);
    }

    private List<ContaBancariaEntity> salvarContaBancariaUseCase(List<ContaBancariaEntity> contaBancariaEntityList){
        try{
            return this.contaBancariaGateway.salvarContasBancarias(contaBancariaEntityList);
        }catch (DataProviderException e){
            throw new UseCaseException(ERRO_SALVAR_LISTA_CONTAS_BANCARIAS_USE_CASE);
        }
    }



}