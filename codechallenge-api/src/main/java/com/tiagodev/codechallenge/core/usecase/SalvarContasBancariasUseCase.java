package com.tiagodev.codechallenge.core.usecase;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.core.exceptions.UseCaseException;
import com.tiagodev.codechallenge.core.usecase.gateway.ContaBancariaGateway;
import com.tiagodev.codechallenge.dataprovider.exceptions.DataProviderException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class SalvarContasBancariasUseCase {

    @Autowired
    private ContaBancariaGateway contaBancariaGateway;

    @Value(value = "${diretorio.arquivo.excel}")
    private String diretorioArquivoExcel;

    public List<ContaBancariaEntity> executar() throws IOException {
        List<ContaBancariaEntity> contaBancariaEntityList = this.converterExcel();
        return this.salvarContaBancariaUseCase(contaBancariaEntityList);
    }

    private List<ContaBancariaEntity> salvarContaBancariaUseCase(List<ContaBancariaEntity> contaBancariaEntityList){
        try{
            return this.contaBancariaGateway.salvarContasBancarias(contaBancariaEntityList);
        }catch (DataProviderException e){
            throw new UseCaseException("Ocorreu um erro ao salvar lista de contas bancarias");
        }
    }

    private List<ContaBancariaEntity> converterExcel() throws IOException {
        FileInputStream file = new FileInputStream(diretorioArquivoExcel);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet worksheet = workbook.getSheetAt(0);

        List<ContaBancariaEntity> contaBancariaTableList = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        for(int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = worksheet.getRow(i);
            contaBancariaTableList.add(ContaBancariaEntity.builder()
                    .nome(row.getCell(0).getStringCellValue())
                    .numeroConta(formatter.formatCellValue(row.getCell(1)))
                    .agencia(formatter.formatCellValue(row.getCell(2)))
                    .chequeEspecialLiberado(row.getCell(3).getNumericCellValue() == 1)
                    .saldo(new BigDecimal(formatter.formatCellValue(row.getCell(4)).replace(".", "").replace(",", ".")))
                    .chequeEspecial(new BigDecimal(formatter.formatCellValue(row.getCell(5)).replace(".", "").replace(",", ".")))
                    .taxa(Double.parseDouble(row.getCell(6).getStringCellValue().replace(".", "").replace(",", ".")))
                    .build());
        }

        return contaBancariaTableList;
    }

}