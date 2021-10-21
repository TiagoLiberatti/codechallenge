package com.tiagodev.codechallenge.core.utils;

import com.opencsv.CSVReader;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterPlanilhaUtil {

    @Value(value = "${diretorio.arquivo.planilha}")
    private String diretorioArquivoPlanilha;

    public List<ContaBancariaEntity> executar(){

        List<ContaBancariaEntity> contaBancariaTableList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(diretorioArquivoPlanilha))) {
            reader.skip(1);
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                if (lineInArray != null) {
                    contaBancariaTableList.add(this.construirContaBancaria(lineInArray));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contaBancariaTableList;
    }

    private ContaBancariaEntity construirContaBancaria(String[] lineInArray){
        return ContaBancariaEntity.builder()
                .nome(lineInArray[0])
                .numeroConta(lineInArray[1])
                .agencia(lineInArray[2])
                .chequeEspecialLiberado(lineInArray[3].equals("1"))
                .saldo(new BigDecimal(lineInArray[4].replace(".", "").replace(",", ".")))
                .chequeEspecial(new BigDecimal(lineInArray[5].replace(".", "").replace(",", ".")))
                .taxa(Double.parseDouble(lineInArray[6].replace(".", "").replace(",", ".")))
                .build();
    }

}