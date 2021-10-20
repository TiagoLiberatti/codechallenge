package com.tiagodev.codechallenge.dataprovider;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.dataprovider.entity.ContaBancariaTable;
import com.tiagodev.codechallenge.dataprovider.exceptions.DataProviderException;
import com.tiagodev.codechallenge.dataprovider.repository.ContaBancariaRepository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class ContaBancariaDataProviderTest {

    @InjectMocks
    private ContaBancariaDataProvider contaBancariaDataProvider;

    @Mock
    private ContaBancariaRepository contaBancariaRepository;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private static final Long ID_CONTA_BANCARIA = 1L;
    private static final String NUMERO_CONTA_BANCARIA = "1234";
    private static final String NOME_CONTA_BANCARIA = "Nome Teste";
    private static final String AGENCIA_CONTA_BANCARIA = "Nome Teste";
    private static final Boolean CHEQUE_ESPECIAL_CONTA_BANCARIA = true;

    private static final Optional<ContaBancariaTable> CONTA_BANCARIA_TABLE_OPTIONAL = Optional.ofNullable(ContaBancariaTable.builder().build());
    private static final ContaBancariaTable CONTA_BANCARIA_TABLE = ContaBancariaTable.builder().build();
    private static final List<ContaBancariaTable> CONTA_BANCARIA_TABLE_LIST = List.of(ContaBancariaTable.builder().build());
    private static final List<ContaBancariaEntity> CONTA_BANCARIA_ENTITY_LIST = List.of(ContaBancariaEntity.builder().build());
    private static final ContaBancariaEntity CONTA_BANCARIA_ENTITY = ContaBancariaEntity.builder().build();

    @Test
    public void deveBuscarContaBancariaPorId() {
        Mockito.when(this.contaBancariaRepository.findById(ID_CONTA_BANCARIA)).thenReturn(CONTA_BANCARIA_TABLE_OPTIONAL);
        contaBancariaDataProvider.buscarContaBancariaPorId(ID_CONTA_BANCARIA);
        Mockito.verify(contaBancariaRepository, Mockito.times(1)).findById(ID_CONTA_BANCARIA);
    }

    @Test
    public void naoDeveBuscarContaBancariaPorIdException() {
        Mockito.when(this.contaBancariaRepository.findById(ID_CONTA_BANCARIA))
                .thenThrow(RecoverableDataAccessException.class);

        expectedException.expect(DataProviderException.class);
        expectedException.expectMessage("Ocorreu um erro ao buscar conta bancaria por ID");

        contaBancariaDataProvider.buscarContaBancariaPorId(ID_CONTA_BANCARIA);
    }

//    @Test
//    public void naoDeveBuscarContaBancariaPorIdNotFoundException() {
//        Mockito.when(this.contaBancariaRepository.findById(ID_CONTA_BANCARIA)).thenReturn(null);
//
//        expectedException.expect(NotFoundException.class);
//        expectedException.expectMessage(String.format("Conta bancária com id %s não existe", ID_CONTA_BANCARIA));
//
//        contaBancariaDataProvider.buscarContaBancariaPorId(ID_CONTA_BANCARIA);
//    }

    @Test
    public void deveBuscarContaBancariaPorNumero() {
        Mockito.when(this.contaBancariaRepository.findOneByNumeroConta(NUMERO_CONTA_BANCARIA)).thenReturn(CONTA_BANCARIA_TABLE);
        contaBancariaDataProvider.buscarContaBancariaPorNumero(NUMERO_CONTA_BANCARIA);
        Mockito.verify(contaBancariaRepository, Mockito.times(1)).findOneByNumeroConta(NUMERO_CONTA_BANCARIA);
    }

    @Test
    public void naoDeveBuscarContaBancariaPorNumeroException() {
        Mockito.when(this.contaBancariaRepository.findOneByNumeroConta(NUMERO_CONTA_BANCARIA))
                .thenThrow(RecoverableDataAccessException.class);

        expectedException.expect(DataProviderException.class);
        expectedException.expectMessage("Ocorreu um erro ao buscar conta bancaria por numero");

        contaBancariaDataProvider.buscarContaBancariaPorNumero(NUMERO_CONTA_BANCARIA);
    }

    @Test
    public void deveBuscarContasBancarias() {
        Mockito.when(this.contaBancariaRepository.findAllByNomeAgenciaChequeEspecial(NOME_CONTA_BANCARIA, AGENCIA_CONTA_BANCARIA, CHEQUE_ESPECIAL_CONTA_BANCARIA))
                .thenReturn(CONTA_BANCARIA_TABLE_LIST);
        contaBancariaDataProvider.buscarContasBancarias(NOME_CONTA_BANCARIA, AGENCIA_CONTA_BANCARIA, CHEQUE_ESPECIAL_CONTA_BANCARIA);
        Mockito.verify(contaBancariaRepository, Mockito.times(1)).findAllByNomeAgenciaChequeEspecial(
                NOME_CONTA_BANCARIA, AGENCIA_CONTA_BANCARIA, CHEQUE_ESPECIAL_CONTA_BANCARIA);
    }

    @Test
    public void naoDeveBuscarContasBancariasException() {
        Mockito.when(this.contaBancariaRepository.findAllByNomeAgenciaChequeEspecial(NOME_CONTA_BANCARIA, AGENCIA_CONTA_BANCARIA, CHEQUE_ESPECIAL_CONTA_BANCARIA))
                .thenThrow(RecoverableDataAccessException.class);

        expectedException.expect(DataProviderException.class);
        expectedException.expectMessage("Ocorreu um erro ao buscar lista de contas bancarias");

        contaBancariaDataProvider.buscarContasBancarias(NOME_CONTA_BANCARIA, AGENCIA_CONTA_BANCARIA, CHEQUE_ESPECIAL_CONTA_BANCARIA);
    }

    @Test
    public void deveSalvarContasBancarias() {
        Mockito.when(this.contaBancariaRepository.saveAll(CONTA_BANCARIA_TABLE_LIST))
                .thenReturn(CONTA_BANCARIA_TABLE_LIST);
        contaBancariaDataProvider.salvarContasBancarias(CONTA_BANCARIA_ENTITY_LIST);
        Mockito.verify(contaBancariaRepository, Mockito.times(1)).saveAll(CONTA_BANCARIA_TABLE_LIST);
    }

    @Test
    public void naoDeveSalvarContasBancariasException() {
        Mockito.when(this.contaBancariaRepository.saveAll(CONTA_BANCARIA_TABLE_LIST)).thenThrow(RecoverableDataAccessException.class);

        expectedException.expect(DataProviderException.class);
        expectedException.expectMessage("Ocorreu um erro ao salvar uma lista de contas bancarias");

        contaBancariaDataProvider.salvarContasBancarias(CONTA_BANCARIA_ENTITY_LIST);
    }

    @Test
    public void deveSalvarContaBancaria() {
        Mockito.when(this.contaBancariaRepository.save(CONTA_BANCARIA_TABLE))
                .thenReturn(CONTA_BANCARIA_TABLE);
        contaBancariaDataProvider.salvarContaBancaria(CONTA_BANCARIA_ENTITY);
        Mockito.verify(contaBancariaRepository, Mockito.times(1)).save(CONTA_BANCARIA_TABLE);
    }

    @Test
    public void naoDeveSalvarContaBancariaException() {
        Mockito.when(this.contaBancariaRepository.save(CONTA_BANCARIA_TABLE)).thenThrow(RecoverableDataAccessException.class);

        expectedException.expect(DataProviderException.class);
        expectedException.expectMessage("Ocorreu um erro ao salvar uma conta bancaria");

        contaBancariaDataProvider.salvarContaBancaria(CONTA_BANCARIA_ENTITY);
    }

    @Test
    public void deveAtualizarContaBancaria() {
        Mockito.when(this.contaBancariaRepository.save(CONTA_BANCARIA_TABLE))
                .thenReturn(CONTA_BANCARIA_TABLE);
        contaBancariaDataProvider.atualizarContaBancaria(CONTA_BANCARIA_ENTITY);
        Mockito.verify(contaBancariaRepository, Mockito.times(1)).save(CONTA_BANCARIA_TABLE);
    }

    @Test
    public void naoDeveAtualizarContaBancariaException() {
        Mockito.when(this.contaBancariaRepository.save(CONTA_BANCARIA_TABLE)).thenThrow(RecoverableDataAccessException.class);

        expectedException.expect(DataProviderException.class);
        expectedException.expectMessage("Ocorreu um erro ao atualizar uma conta bancaria");

        contaBancariaDataProvider.atualizarContaBancaria(CONTA_BANCARIA_ENTITY);
    }

    @Test
    public void deveDeletarContaBancaria() {
        contaBancariaDataProvider.deletarContaBancaria(ID_CONTA_BANCARIA);
        Mockito.verify(contaBancariaRepository, Mockito.times(1)).deleteById(ID_CONTA_BANCARIA);
    }

    @Test
    public void naoDeveDeletarContaBancariaException() {
        Mockito.doThrow(RecoverableDataAccessException.class).when(contaBancariaRepository).deleteById(ID_CONTA_BANCARIA);

        expectedException.expect(DataProviderException.class);
        expectedException.expectMessage("Ocorreu um erro ao deletar uma conta bancaria");

        contaBancariaDataProvider.deletarContaBancaria(ID_CONTA_BANCARIA);
    }

}