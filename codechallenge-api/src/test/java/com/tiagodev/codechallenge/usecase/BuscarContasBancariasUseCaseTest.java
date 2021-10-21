package com.tiagodev.codechallenge.usecase;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.core.exceptions.UseCaseException;
import com.tiagodev.codechallenge.core.usecase.BuscarContasBancariasUseCase;
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

import static com.tiagodev.codechallenge.utils.MessageExceptionUtil.ERRO_BUSCAR_LISTA_CONTAS_BANCARIAS_USE_CASE;

@RunWith(SpringRunner.class)
public class BuscarContasBancariasUseCaseTest {

    @InjectMocks
    private BuscarContasBancariasUseCase buscarContasBancariasUseCase;

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
        Mockito.when(this.contaBancariaGateway.buscarContaBancariaPorId(Mockito.anyLong())).thenReturn(CONTA_BANCARIA_ENTITY);
        buscarContasBancariasUseCase.executar(Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean());
        Mockito.verify(contaBancariaGateway, Mockito.times(1)).buscarContasBancarias(Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean());
    }

    @Test
    public void naoDeveExecutar(){
        Mockito.when(this.contaBancariaGateway.buscarContasBancarias(Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean())).thenThrow(DataProviderException.class);

        expectedException.expect(UseCaseException.class);
        expectedException.expectMessage(ERRO_BUSCAR_LISTA_CONTAS_BANCARIAS_USE_CASE);

        buscarContasBancariasUseCase.executar(Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean());
    }

}