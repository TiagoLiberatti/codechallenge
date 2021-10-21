package com.tiagodev.codechallenge.dataprovider.repository;

import com.tiagodev.codechallenge.dataprovider.entity.ContaBancariaTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancariaTable, Long>{

    @Query(value = "SELECT contaBancaria FROM ContaBancariaTable contaBancaria " +
                   "WHERE (LOWER(contaBancaria.nome) LIKE LOWER(CONCAT('%',:nome,'%')) OR :nome IS NULL) " +
                   "AND (contaBancaria.agencia = :agencia OR :agencia IS NULL) " +
                   "AND (contaBancaria.chequeEspecialLiberado = :chequeEspecialLiberado OR :chequeEspecialLiberado IS NULL)")
    List<ContaBancariaTable> findAllByNomeAgenciaChequeEspecial(@Param("nome") String nome, @Param("agencia") String agencia,
                                                                @Param("chequeEspecialLiberado") Boolean chequeEspecialLiberado);

    ContaBancariaTable findOneByNumeroConta(String numeroContaBancaria);
}