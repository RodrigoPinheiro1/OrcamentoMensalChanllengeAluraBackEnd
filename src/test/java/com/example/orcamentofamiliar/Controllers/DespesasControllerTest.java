package com.example.orcamentofamiliar.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser(username = "teste@gmail.com", authorities = {"USUARIO"}, password = "123456")
class DespesasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveriaDevolver409AoAtualizarMesmaDescricaoNoMesmoMes() throws Exception {
        URI uri = new URI("/despesas/1");

        String json = "{\"descricao\":\"alugelD\", \"valor\":\"18\",\"data\":\"2022-06-20\",\"categorias\":\"OUTRAS\" }";

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isConflict());

    }


    @Test
    void deveriaDevolver201AoCadastrar() throws Exception {


        URI uri = new URI("/despesas");

        String json = "{\"categorias\":\"OUTRAS\",\"data\":\"2022-10-20\",\"valor\":\"18\",\"descricao\":\"descricaonaorepetida\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }


    @Test
    void deveriaDevolverConflictAoCadastrarComMesmaDescricaoDentroDoMesmoMes() throws Exception {


        URI uri = new URI("/despesas");

        String json = "{\"descricao\":\"alugelD\", \"valor\":\"18\",\"data\":\"2022-06-20\",\"categorias\":\"OUTRAS\" }";
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isConflict());

    }

    @Test
    void deveriaDevolver200AoAtualizarDescricaoEdata() throws Exception {
        URI uri = new URI("/despesas/1");

        String json = "{\"descricao\":\"atualizacao\",\"valor\":\"18\",\"data\":\"2022-06-20\",\"categorias\":\"SAUDE\"}";


        mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    void deveriaDevolver404AoAtualizarUmIdinvalido() throws Exception {
        URI uri = new URI("/despesas/26262");

        String json = "{\"descricao\":\"descricaoAtu\",\"valor\":\"18\",\"data\":\"2022-06-20\",\"categorias\":\"OUTRAS\"}";

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }



    @Test
    void deveriaBuscarPorIdEDevolver200() throws Exception {

        URI uri = new URI("/despesas/1");
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    void deveriaDevolver404IdInvalidoAoBuscarId() throws Exception {

        URI uri = new URI("/despesas/484548");
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void deveriaExcluirComIdValidoDevolvendo200() throws Exception {
        URI uri = new URI("/despesas/3");
        mockMvc.perform(MockMvcRequestBuilders.delete(uri))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void deveriaDevolver404IdInvalidoParaExclusao() throws Exception {
        URI uri = new URI("/despesas/4815812181");
        mockMvc.perform(MockMvcRequestBuilders.delete(uri))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}
