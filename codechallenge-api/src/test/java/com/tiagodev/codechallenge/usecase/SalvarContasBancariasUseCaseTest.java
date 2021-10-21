package com.tiagodev.codechallenge.usecase;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.core.exceptions.UseCaseException;
import com.tiagodev.codechallenge.core.usecase.SalvarContasBancariasUseCase;
import com.tiagodev.codechallenge.core.usecase.gateway.ContaBancariaGateway;
import com.tiagodev.codechallenge.core.utils.ConverterPlanilhaUtil;
import com.tiagodev.codechallenge.dataprovider.exceptions.DataProviderException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
public class SalvarContasBancariasUseCaseTest {

    @InjectMocks
    private SalvarContasBancariasUseCase salvarContasBancariasUseCase;

    @Mock
    private ContaBancariaGateway contaBancariaGateway;

    @Mock
    private ConverterPlanilhaUtil converterArquivoPlanilhaUtil;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private static final List<ContaBancariaEntity> CONTA_BANCARIA_ENTITY_LIST = List.of(ContaBancariaEntity.builder()
            .id(1L)
            .nome("Nome Teste")
            .numeroConta("1234-1")
            .agencia("1234")
            .chequeEspecialLiberado(true)
            .saldo(new BigDecimal(1000))
            .chequeEspecial(new BigDecimal(100))
            .taxa(2.0)
            .build());

    @Test
    public void deveExecutar() throws IOException {
        Mockito.when(converterArquivoPlanilhaUtil.executar()).thenReturn(CONTA_BANCARIA_ENTITY_LIST);
        salvarContasBancariasUseCase.executar();
        Mockito.verify(contaBancariaGateway, Mockito.times(1)).salvarContasBancarias(CONTA_BANCARIA_ENTITY_LIST);
    }

    @Test
    public void naoDeveExecutar() throws IOException {
        Mockito.when(converterArquivoPlanilhaUtil.executar()).thenReturn(CONTA_BANCARIA_ENTITY_LIST);

        Mockito.when(this.contaBancariaGateway.salvarContasBancarias(CONTA_BANCARIA_ENTITY_LIST)).thenThrow(DataProviderException.class);

        expectedException.expect(UseCaseException.class);
        expectedException.expectMessage("Ocorreu um erro ao salvar lista de contas");

        salvarContasBancariasUseCase.executar();
    }

}