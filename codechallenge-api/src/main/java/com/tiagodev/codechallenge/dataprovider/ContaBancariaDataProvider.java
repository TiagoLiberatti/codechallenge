package com.tiagodev.codechallenge.dataprovider;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.core.usecase.gateway.ContaBancariaGateway;
import com.tiagodev.codechallenge.dataprovider.entity.ContaBancariaTable;
import com.tiagodev.codechallenge.dataprovider.exceptions.DataProviderException;
import com.tiagodev.codechallenge.dataprovider.exceptions.NotFoundException;
import com.tiagodev.codechallenge.dataprovider.mapper.ContaBancariaMapper;
import com.tiagodev.codechallenge.dataprovider.repository.ContaBancariaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

import static com.tiagodev.codechallenge.utils.MessageExceptionUtil.*;

@Component
public class ContaBancariaDataProvider implements ContaBancariaGateway {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Override
    public ContaBancariaEntity buscarContaBancariaPorId(Long idContaBancaria) {
        try{
            ContaBancariaTable contaBancaria = this.contaBancariaRepository.findById(idContaBancaria).orElseThrow(() ->
                    new NotFoundException(String.format(CONTA_BANCARIA_ID_NAO_EXISTE, idContaBancaria)));
            return ContaBancariaMapper.INSTANCE.tableToEntity(contaBancaria);
        }catch (DataAccessException e){
            throw new DataProviderException(ERRO_BUSCAR_CONTA_BANCARIA_POR_ID);
        }
    }

    @Override
    public ContaBancariaEntity buscarContaBancariaPorNumero(String idContaBancaria) {
        try{
            ContaBancariaTable contaBancaria = this.contaBancariaRepository.findOneByNumeroConta(idContaBancaria);

            if(contaBancaria == null)
                throw new NotFoundException(String.format(CONTA_BANCARIA_NUMERO_NAO_EXISTE, idContaBancaria));

            return ContaBancariaMapper.INSTANCE.tableToEntity(contaBancaria);
        }catch (DataAccessException e){
            throw new DataProviderException(ERRO_BUSCAR_CONTA_BANCARIA_POR_NUMERO);
        }
    }

    @Override
    public List<ContaBancariaEntity> buscarContasBancarias(@RequestParam String nome, @RequestParam String agencia, @RequestParam Boolean chequeEspecialLiberado) {
        try{
            List<ContaBancariaTable> contasBancarias = this.contaBancariaRepository.findAllByNomeAgenciaChequeEspecial(nome, agencia, chequeEspecialLiberado);
            return contasBancarias.stream().map(ContaBancariaMapper.INSTANCE::tableToEntity).collect(Collectors.toList());
        }catch (DataAccessException e){
            throw new DataProviderException(ERRO_BUSCAR_LISTA_CONTAS_BANCARIAS);
        }
    }

    @Override
    public List<ContaBancariaEntity> salvarContasBancarias(List<ContaBancariaEntity> contasBancarias) {
        try{
            List<ContaBancariaTable> contasBancariasTableList = contasBancarias.stream().map(
                    ContaBancariaMapper.INSTANCE::entityListToTableList).collect(Collectors.toList()); ;
            List<ContaBancariaTable> contasBancariasList = this.contaBancariaRepository.saveAll(contasBancariasTableList);
            return contasBancariasList.stream().map(ContaBancariaMapper.INSTANCE::tableToEntity).collect(Collectors.toList());
        }catch (DataAccessException e){
            throw new DataProviderException(ERRO_SALVAR_LISTA_CONTAS_BANCARIAS);
        }
    }

    @Override
    public ContaBancariaEntity salvarContaBancaria(ContaBancariaEntity contaBancaria) {
        try{
            ContaBancariaTable contaBancariaTable = ContaBancariaMapper.INSTANCE.entityListToTableList(contaBancaria);
            return ContaBancariaMapper.INSTANCE.tableToEntity(this.contaBancariaRepository.save(contaBancariaTable));
        }catch (DataAccessException e){
            throw new DataProviderException(ERRO_SALVAR_CONTA_BANCARIA);
        }
    }

    @Override
    public ContaBancariaEntity atualizarContaBancaria(ContaBancariaEntity contaBancaria) {
        try{
            ContaBancariaTable contaBancariaTable = ContaBancariaMapper.INSTANCE.entityListToTableList(contaBancaria);
            return ContaBancariaMapper.INSTANCE.tableToEntity(this.contaBancariaRepository.save(contaBancariaTable));
        }catch (DataAccessException e){
            throw new DataProviderException(ERRO_ATUALIZAR_CONTA_BANCARIA);
        }
    }

    @Override
    public void deletarContaBancaria(Long idContaBancaria) {
        try{
            this.contaBancariaRepository.deleteById(idContaBancaria);
        }catch (DataAccessException e){
            throw new DataProviderException(ERRO_DELETAR_CONTA_BANCARIA);
        }
    }

}