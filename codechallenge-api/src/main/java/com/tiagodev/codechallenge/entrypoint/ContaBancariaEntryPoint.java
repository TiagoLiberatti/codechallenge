package com.tiagodev.codechallenge.entrypoint;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.core.usecase.*;
import com.tiagodev.codechallenge.entrypoint.entity.ContaBancariaDetalheHttpResponse;
import com.tiagodev.codechallenge.entrypoint.entity.ContaBancariaHttpModel;
import com.tiagodev.codechallenge.entrypoint.entity.ContaBancariaHttpResponse;
import com.tiagodev.codechallenge.entrypoint.mapper.ContaBancariaHttpMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/contas-bancarias")
public class ContaBancariaEntryPoint {

    private final BuscarContasBancariasUseCase buscarContasBancariasUseCase;
    private final SalvarContaBancariaUseCase salvarContaBancariaUseCase;
    private final AtualizarContaBancariaUseCase atualizarContaBancariaUseCase;
    private final DeletarContaBancariaUseCase deletarContaBancariaUseCase;
    private final BuscarContaBancariaPorNumeroUseCase buscarContaBancariaPorNumeroUseCase;
    private final BuscarContaBancariaPorIdUseCase buscarContaBancariaPorIdUseCase;

    @Autowired
    public ContaBancariaEntryPoint(BuscarContasBancariasUseCase buscarContasBancariasUseCase,
                                   SalvarContaBancariaUseCase salvarContaBancariaUseCase,
                                   AtualizarContaBancariaUseCase atualizarContaBancariaUseCase,
                                   DeletarContaBancariaUseCase deletarContaBancariaUseCase,
                                   BuscarContaBancariaPorNumeroUseCase buscarContaBancariaPorNumeroUseCase,
                                   BuscarContaBancariaPorIdUseCase buscarContaBancariaPorIdUseCase){
        this.buscarContasBancariasUseCase = buscarContasBancariasUseCase;
        this.salvarContaBancariaUseCase = salvarContaBancariaUseCase;
        this.atualizarContaBancariaUseCase = atualizarContaBancariaUseCase;
        this.deletarContaBancariaUseCase = deletarContaBancariaUseCase;
        this.buscarContaBancariaPorNumeroUseCase = buscarContaBancariaPorNumeroUseCase;
        this.buscarContaBancariaPorIdUseCase = buscarContaBancariaPorIdUseCase;
    }

    @GetMapping
    public ResponseEntity<List<ContaBancariaHttpResponse>> buscarContasBancarias(String nome, String agencia, Boolean chequeEspecialLiberado){

        List<ContaBancariaEntity> contaBancariaEntityList = this.buscarContasBancariasUseCase.executar(nome, agencia, chequeEspecialLiberado);

        if (contaBancariaEntityList == null || contaBancariaEntityList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(contaBancariaEntityList.stream().map(
                ContaBancariaHttpMapper.INSTANCE::entityToHttpResponse).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ContaBancariaHttpResponse> salvarContaBancaria(@RequestBody ContaBancariaHttpModel contaBancaria){

        ContaBancariaEntity contasBancariasEntityList = this.salvarContaBancariaUseCase.executar(
                ContaBancariaHttpMapper.INSTANCE.httpModelToEntity(contaBancaria));

        return new ResponseEntity<>(ContaBancariaHttpMapper.INSTANCE.entityToHttpResponse(contasBancariasEntityList), null, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{numeroContaBancaria}")
    public ResponseEntity<ContaBancariaHttpResponse> buscarContaBancariaPorId(@PathVariable Long numeroContaBancaria){

        ContaBancariaEntity contaBancariaEntity = this.buscarContaBancariaPorIdUseCase.executar(numeroContaBancaria);

        if (contaBancariaEntity == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(ContaBancariaHttpMapper.INSTANCE.entityToHttpResponse(contaBancariaEntity));
    }

    @GetMapping(path = "/detalhes/{numeroContaBancaria}")
    public ResponseEntity<ContaBancariaDetalheHttpResponse> buscarDetalhesContaBancariaPorNumero(@PathVariable String numeroContaBancaria){

        ContaBancariaDetalheHttpResponse contaBancariaDetalheHttpResponse = this.buscarContaBancariaPorNumeroUseCase.executar(numeroContaBancaria);

        if (contaBancariaDetalheHttpResponse == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(contaBancariaDetalheHttpResponse);
    }

    @PutMapping(path = "/{idContaBancaria}")
    public ResponseEntity<ContaBancariaHttpResponse> atualizarContaBancaria(@RequestBody ContaBancariaHttpModel contaBancaria,
                                                                            @PathVariable Long idContaBancaria){

        ContaBancariaEntity contasBancariasEntityList = this.atualizarContaBancariaUseCase.executar(
                ContaBancariaHttpMapper.INSTANCE.httpModelToEntity(contaBancaria), idContaBancaria);

        return ResponseEntity.ok(ContaBancariaHttpMapper.INSTANCE.entityToHttpResponse(contasBancariasEntityList));
    }

    @DeleteMapping(path = "/{idContaBancaria}")
    public ResponseEntity<Void> deletarContaBancaria(@PathVariable Long idContaBancaria){

        this.deletarContaBancariaUseCase.executar(idContaBancaria);

        return ResponseEntity.noContent().build();
    }

}