package com.tiagodev.codechallenge.entrypoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.core.usecase.*;
import com.tiagodev.codechallenge.entrypoint.entity.ContaBancariaDetalheHttpResponse;
import com.tiagodev.codechallenge.entrypoint.entity.ContaBancariaHttpModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@RunWith(SpringRunner.class)
public class ContaBancariaEntryPointTest {

    @InjectMocks
    private ContaBancariaEntryPoint contaBancariaEntryPoint;

    @Mock
    private BuscarContasBancariasUseCase buscarContasBancariasUseCase;

    @Mock
    private SalvarContaBancariaUseCase salvarContaBancariaUseCase;

    @Mock
    private AtualizarContaBancariaUseCase atualizarContaBancariaUseCase;

    @Mock
    private DeletarContaBancariaUseCase deletarContaBancariaUseCase;

    @Mock
    private BuscarContaBancariaPorNumeroUseCase buscarContaBancariaPorNumeroUseCase;

    @Mock
    private BuscarContaBancariaPorIdUseCase buscarContaBancariaPorIdUseCase;

    private MockMvc mockMvc;

    private static final String NOME_CONTA_BANCARIA = "Nome Teste";
    private static final String AGENCIA_CONTA_BANCARIA = "Nome Teste";
    private static final Boolean CHEQUE_ESPECIAL_CONTA_BANCARIA = true;
    private static final Long ID_CONTA_BANCARIA = 1L;
    private static final String NUMERO_CONTA_BANCARIA = "12345";
    private static final ContaBancariaHttpModel CONTA_BANCARIA_HTTP_MODEL = ContaBancariaHttpModel.builder().build();
    private static final ContaBancariaEntity CONTA_BANCARIA_ENTITY = ContaBancariaEntity.builder().build();
    private static final ContaBancariaDetalheHttpResponse CONTA_BANCARIA_DETALHE_HTTP_RESPONSE = ContaBancariaDetalheHttpResponse.builder().build();
    private static final List<ContaBancariaEntity> CONTA_BANCARIA_ENTITY_LIST = List.of(ContaBancariaEntity.builder().build());

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.contaBancariaEntryPoint).build();
    }

    @Test
    public void deveBuscarContasBancariasOk() throws Exception {
        Mockito.when(buscarContasBancariasUseCase.executar(NOME_CONTA_BANCARIA, AGENCIA_CONTA_BANCARIA, CHEQUE_ESPECIAL_CONTA_BANCARIA))
                .thenReturn(CONTA_BANCARIA_ENTITY_LIST);
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/contas-bancarias?nome="+NOME_CONTA_BANCARIA+"&agencia="+
                                AGENCIA_CONTA_BANCARIA+"&chequeEspecialLiberado="+CHEQUE_ESPECIAL_CONTA_BANCARIA)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveBuscarContasBancariasNoContent() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/contas-bancarias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deveSalvarContaBancaria() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/contas-bancarias")
                        .content(this.objectToJson(CONTA_BANCARIA_HTTP_MODEL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void deveBuscarContaBancariaPorIdOk() throws Exception {
        Mockito.when(buscarContaBancariaPorIdUseCase.executar(Mockito.anyLong())).thenReturn(CONTA_BANCARIA_ENTITY);
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/contas-bancarias/"+ID_CONTA_BANCARIA)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveBuscarContaBancariaPorIdNoContent() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/contas-bancarias/"+ID_CONTA_BANCARIA)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deveBuscarDetalhesContaBancariaPorNumeroOk() throws Exception {
        Mockito.when(buscarContaBancariaPorNumeroUseCase.executar(NUMERO_CONTA_BANCARIA)).thenReturn(CONTA_BANCARIA_DETALHE_HTTP_RESPONSE);
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/contas-bancarias/detalhes/"+NUMERO_CONTA_BANCARIA)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveBuscarDetalhesContaBancariaPorNumeroNoContent() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/contas-bancarias/detalhes/"+ID_CONTA_BANCARIA)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deveAtualizarContaBancaria() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/contas-bancarias/"+ID_CONTA_BANCARIA)
                        .content(this.objectToJson(CONTA_BANCARIA_HTTP_MODEL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveDeletarContaBancaria() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/contas-bancarias/"+ID_CONTA_BANCARIA)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    protected String objectToJson(final Object obj){
        try{
            final ObjectMapper mapper = new ObjectMapper();
            final String json = mapper.writeValueAsString(obj);
            return json;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}