package com.tiagodev.codechallenge.usecase;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.core.exceptions.UseCaseException;
import com.tiagodev.codechallenge.core.usecase.AtualizarContaBancariaUseCase;
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
public class AtualizarContaBancariaUseCaseTest {

    @InjectMocks
    private AtualizarContaBancariaUseCase atualizarContaBancariaUseCase;

    @Mock
    private ContaBancariaGateway contaBancariaGateway;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private static final Long ID_CONTA_BANCARIA = 1L;
    private static final ContaBancariaEntity CONTA_BANCARIA_ENTITY = ContaBancariaEntity.builder()
            .id(1L)
            .nome("Nome Teste")
            .numeroConta("1234-1")
            .agencia("1234")
            .chequeEspecialLiberado(true)
            .saldo(new BigDecimal(1000))
            .chequeEspecial(new BigDecimal(1000))
            .taxa(2.0)
            .build();

    @Test
    public void deveExecutar(){
        Mockito.when(this.contaBancariaGateway.buscarContaBancariaPorId(Mockito.anyLong())).thenReturn(CONTA_BANCARIA_ENTITY);
        atualizarContaBancariaUseCase.executar(CONTA_BANCARIA_ENTITY, ID_CONTA_BANCARIA);
        Mockito.verify(contaBancariaGateway, Mockito.times(1)).buscarContaBancariaPorId(ID_CONTA_BANCARIA);
        Mockito.verify(contaBancariaGateway, Mockito.times(1)).atualizarContaBancaria(CONTA_BANCARIA_ENTITY);
    }

    @Test
    public void naoDeveExecutar(){
        Mockito.when(this.contaBancariaGateway.buscarContaBancariaPorId(Mockito.anyLong())).thenReturn(CONTA_BANCARIA_ENTITY);
        Mockito.when(this.contaBancariaGateway.atualizarContaBancaria(CONTA_BANCARIA_ENTITY)).thenThrow(DataProviderException.class);

        expectedException.expect(UseCaseException.class);
        expectedException.expectMessage("Ocorreu um erro ao atualizar conta bancaria");

        atualizarContaBancariaUseCase.executar(CONTA_BANCARIA_ENTITY, ID_CONTA_BANCARIA);
    }

}