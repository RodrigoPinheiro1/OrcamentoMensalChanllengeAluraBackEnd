package com.example.orcamentofamiliar.Controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ReceitaControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void deveriaDevolver409PoisDescricaoComMesmoNomeEMesmoMes() throws Exception {

        URI uri = new URI("/receitas");

        String json = "{\"descricao\":\"descricao\", \"valor\":\"18\",\"data\":\"2022-06-20\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isConflict());

    }

    @Test
    void deveriaDevolver201DescricaoDiferentesDoMesmoMes() throws Exception {

        URI uri = new URI("/receitas");

        String json = "{\"descricao\":\"descricao\", \"valor\":\"18\",\"data\":\"2022-02-20\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void deveriaDevolver200PassandoIdCorreto() throws Exception {

        URI uri = new URI("/receitas/1");
        String json = "{\"descricao\":\"descricaoAtu\", \"valor\":\"18\",\"data\":\"2022-02-20\"}";

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    void deveriaDevolverNotFoundIdInfalido() throws Exception {

        URI uri = new URI("/receitas/8");
        String json = "{\"descricao\":\"descricaoAtu\", \"valor\":\"18\",\"data\":\"2022-02-20\"}";

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
    @Test
    void deveriaDevolverConlflictAtualizacaoComMesmaDescricaoEDataMesmoMes() throws Exception {

        URI uri = new URI("/receitas/1");
        String json = "{\"descricao\":\"descricaoAtu\", \"valor\":\"18\",\"data\":\"2022-02-20\"}";

       mockMvc.perform(MockMvcRequestBuilders.put(uri)
               .content(json)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().isConflict());

    }

    @Test
    void deveriaDesevolverNotFoundComIdInvalido() throws Exception {

        URI uri = new URI("/receitas/8");

        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    void deveriaBuscarPeloIdEDevolver200PassandoIdCorreto() throws Exception {

        URI uri = new URI("/receitas/2");

        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void listarTudoOuNome() {


    }
    @Test
    void deveriaDevolver404AoDeletar() throws Exception {
        URI uri = new URI("/receitas/654");

        mockMvc.perform(MockMvcRequestBuilders.delete(uri))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void deveriaDevolver200AoDeletar() throws Exception {
        URI uri = new URI("/receitas/2");

        mockMvc.perform(MockMvcRequestBuilders.delete(uri))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
