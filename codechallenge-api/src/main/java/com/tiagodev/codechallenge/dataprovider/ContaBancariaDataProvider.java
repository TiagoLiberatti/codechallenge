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

@Component
public class ContaBancariaDataProvider implements ContaBancariaGateway {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Override
    public ContaBancariaEntity buscarContaBancariaPorId(Long idContaBancaria) {
        try{
            ContaBancariaTable contaBancaria = this.contaBancariaRepository.findById(idContaBancaria).orElseThrow(() ->
                    new NotFoundException(String.format("Conta bancária com id %s não existe", idContaBancaria)));
            return ContaBancariaMapper.INSTANCE.tableToEntity(contaBancaria);
        }catch (DataAccessException e){
            throw new DataProviderException("Ocorreu um erro ao buscar conta bancaria por ID");
        }
    }

    @Override
    public ContaBancariaEntity buscarContaBancariaPorNumero(String idContaBancaria) {
        try{
            ContaBancariaTable contaBancaria = this.contaBancariaRepository.findOneByNumeroConta(idContaBancaria);

            if(contaBancaria == null)
                throw new NotFoundException(String.format("Conta bancária de número %s não existe", idContaBancaria));

            return ContaBancariaMapper.INSTANCE.tableToEntity(contaBancaria);
        }catch (DataAccessException e){
            throw new DataProviderException("Ocorreu um erro ao buscar conta bancaria por numero");
        }
    }

    @Override
    public List<ContaBancariaEntity> buscarContasBancarias(@RequestParam String nome, @RequestParam String agencia, @RequestParam Boolean chequeEspecialLiberado) {
        try{
            List<ContaBancariaTable> contasBancarias = this.contaBancariaRepository.findAllByNomeAgenciaChequeEspecial(nome, agencia, chequeEspecialLiberado);
            return contasBancarias.stream().map(ContaBancariaMapper.INSTANCE::tableToEntity).collect(Collectors.toList());
        }catch (DataAccessException e){
            throw new DataProviderException("Ocorreu um erro ao buscar lista de contas bancarias");
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
            throw new DataProviderException("Ocorreu um erro ao salvar uma lista de contas bancarias");
        }
    }

    @Override
    public ContaBancariaEntity salvarContaBancaria(ContaBancariaEntity contaBancaria) {
        try{
            ContaBancariaTable contaBancariaTable = ContaBancariaMapper.INSTANCE.entityListToTableList(contaBancaria);
            return ContaBancariaMapper.INSTANCE.tableToEntity(this.contaBancariaRepository.save(contaBancariaTable));
        }catch (DataAccessException e){
            throw new DataProviderException("Ocorreu um erro ao salvar uma conta bancaria");
        }
    }

    @Override
    public ContaBancariaEntity atualizarContaBancaria(ContaBancariaEntity contaBancaria) {
        try{
            ContaBancariaTable contaBancariaTable = ContaBancariaMapper.INSTANCE.entityListToTableList(contaBancaria);
            return ContaBancariaMapper.INSTANCE.tableToEntity(this.contaBancariaRepository.save(contaBancariaTable));
        }catch (DataAccessException e){
            throw new DataProviderException("Ocorreu um erro ao atualizar uma conta bancaria");
        }
    }

    @Override
    public void deletarContaBancaria(Long idContaBancaria) {
        try{
            this.contaBancariaRepository.deleteById(idContaBancaria);
        }catch (DataAccessException e){
            throw new DataProviderException("Ocorreu um erro ao deletar uma conta bancaria");
        }
    }

}