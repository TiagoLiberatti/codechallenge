package com.tiagodev.codechallenge.usecase;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.core.exceptions.UseCaseException;
import com.tiagodev.codechallenge.core.usecase.BuscarContaBancariaPorNumeroUseCase;
import com.tiagodev.codechallenge.core.usecase.gateway.ContaBancariaGateway;
import com.tiagodev.codechallenge.dataprovider.exceptions.DataProviderException;
import com.tiagodev.codechallenge.entrypoint.entity.ContaBancariaDetalheHttpResponse;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static com.tiagodev.codechallenge.utils.MessageExceptionUtil.ERRO_BUSCAR_CONTA_BANCARIA_POR_NUMERO_USE_CASE;

@RunWith(SpringRunner.class)
public class BuscarContaBancariaPorNumeroUseCaseTest {

    @InjectMocks
    private BuscarContaBancariaPorNumeroUseCase buscarContaBancariaPorNumeroUseCase;

    @Mock
    private ContaBancariaGateway contaBancariaGateway;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private static final String NUMERO_CONTA_BANCARIA = "1";
    private static final ContaBancariaEntity CONTA_BANCARIA_ENTITY = ContaBancariaEntity.builder()
            .id(1L)
            .nome("Nome Teste")
            .numeroConta("1234-1")
            .agencia("1234")
            .chequeEspecialLiberado(true)
            .saldo(new BigDecimal(1000))
            .chequeEspecial(new BigDecimal(100))
            .taxa(2.0)
            .build();
    private static final ContaBancariaDetalheHttpResponse CONTA_BANCARIA_DETALHE = ContaBancariaDetalheHttpResponse.builder()
            .nome("Nome Teste")
            .agenciaNumero("1234/1234-1")
            .saldo("R$ "+ new BigDecimal(1000))
            .chequeEspecialLiberado("Liberado")
            .valorChequeEspecialDiaSeguinte("R$ "+102.00)
            .build();

    @Test
    public void deveExecutar(){
        Mockito.when(this.contaBancariaGateway.buscarContaBancariaPorNumero(Mockito.anyString())).thenReturn(CONTA_BANCARIA_ENTITY);
        ContaBancariaDetalheHttpResponse contaBancariaDetalheHttpResponse = buscarContaBancariaPorNumeroUseCase.executar(NUMERO_CONTA_BANCARIA);
        Mockito.verify(contaBancariaGateway, Mockito.times(1)).buscarContaBancariaPorNumero(NUMERO_CONTA_BANCARIA);
        Assert.assertEquals(CONTA_BANCARIA_DETALHE, contaBancariaDetalheHttpResponse);
    }

    @Test
    public void naoDeveExecutar(){
        Mockito.when(this.contaBancariaGateway.buscarContaBancariaPorNumero(Mockito.anyString())).thenThrow(DataProviderException.class);

        expectedException.expect(UseCaseException.class);
        expectedException.expectMessage(ERRO_BUSCAR_CONTA_BANCARIA_POR_NUMERO_USE_CASE);

        buscarContaBancariaPorNumeroUseCase.executar(NUMERO_CONTA_BANCARIA);
    }

}