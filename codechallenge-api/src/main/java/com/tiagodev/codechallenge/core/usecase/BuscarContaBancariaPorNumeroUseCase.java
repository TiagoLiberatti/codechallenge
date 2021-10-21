package com.tiagodev.codechallenge.core.usecase;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.core.exceptions.UseCaseException;
import com.tiagodev.codechallenge.core.usecase.gateway.ContaBancariaGateway;
import com.tiagodev.codechallenge.dataprovider.exceptions.DataProviderException;
import com.tiagodev.codechallenge.entrypoint.entity.ContaBancariaDetalheHttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.tiagodev.codechallenge.utils.MessageExceptionUtil.ERRO_BUSCAR_CONTA_BANCARIA_POR_NUMERO_USE_CASE;

@Component
public class BuscarContaBancariaPorNumeroUseCase {

    @Autowired
    private ContaBancariaGateway contaBancariaGateway;

    private static final String CHEQUE_LIBERADO = "Liberado";
    private static final String CHEQUE_NAO_LIBERADO = "Não Liberado";

    public ContaBancariaDetalheHttpResponse executar(String numeroContaBancaria){
        return this.gerarDetalhesContaBancaria(this.buscarContaBancaria(numeroContaBancaria));
    }

    private ContaBancariaEntity buscarContaBancaria(String numeroContaBancaria){
        try{
            return this.contaBancariaGateway.buscarContaBancariaPorNumero(numeroContaBancaria);
        }catch (DataProviderException e){
            throw new UseCaseException(ERRO_BUSCAR_CONTA_BANCARIA_POR_NUMERO_USE_CASE);
        }
    }

    private ContaBancariaDetalheHttpResponse gerarDetalhesContaBancaria(ContaBancariaEntity contaBancariaEntity){
        double valorChequeEspecial = ((contaBancariaEntity.getTaxa() * contaBancariaEntity.getChequeEspecial().doubleValue()) / 100) + contaBancariaEntity.getChequeEspecial().doubleValue();
        return ContaBancariaDetalheHttpResponse.builder()
                .nome(contaBancariaEntity.getNome())
                .agenciaNumero(contaBancariaEntity.getAgencia()+"/"+contaBancariaEntity.getNumeroConta())
                .saldo("R$ "+contaBancariaEntity.getSaldo())
                .chequeEspecialLiberado(contaBancariaEntity.getChequeEspecialLiberado()? CHEQUE_LIBERADO : CHEQUE_NAO_LIBERADO)
                .valorChequeEspecialDiaSeguinte("R$ "+valorChequeEspecial)
                .build();
    }

}