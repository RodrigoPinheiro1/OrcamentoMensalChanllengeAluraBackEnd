package com.example.orcamentofamiliar.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveriaDevolver201AoCadastrarUsuario() throws Exception {

        String json = "{\"nome\":\"nome\", \"email\":\"saci2@gmail.com\", \"senha\":\"123456\"}";
        URI uri = new URI("/usuario");
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    void deveriaDevolver409PassandoEmailJaExistente () throws Exception {

        String json = "{\"nome\":\"nome\", \"email\":\"teste@gmail.com\", \"senha\":\"123456\"}";
        URI uri = new URI("/usuario");
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }
}
