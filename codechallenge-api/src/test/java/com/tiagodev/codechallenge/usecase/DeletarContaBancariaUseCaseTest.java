package com.tiagodev.codechallenge.usecase;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.core.exceptions.UseCaseException;
import com.tiagodev.codechallenge.core.usecase.DeletarContaBancariaUseCase;
import com.tiagodev.codechallenge.core.usecase.gateway.ContaBancariaGateway;
import com.tiagodev.codechallenge.dataprovider.exceptions.DataProviderException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
public class DeletarContaBancariaUseCaseTest {

    @InjectMocks
    private DeletarContaBancariaUseCase deletarContaBancariaUseCase;

    @Mock
    private ContaBancariaGateway contaBancariaGateway;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

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

    @Test
    public void deveExecutar(){
        deletarContaBancariaUseCase.executar(Mockito.anyLong());
        Mockito.verify(contaBancariaGateway, Mockito.times(1)).deletarContaBancaria(Mockito.anyLong());
    }

    @Test
    public void naoDeveExecutar(){
        Mockito.doThrow(DataProviderException.class).when(this.contaBancariaGateway).deletarContaBancaria(Mockito.anyLong());

        expectedException.expect(UseCaseException.class);
        expectedException.expectMessage("Ocorreu um erro ao deletar uma conta");

        deletarContaBancariaUseCase.executar(Mockito.anyLong());
    }

}