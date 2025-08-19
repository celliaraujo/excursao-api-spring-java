package br.com.ca.vou_de_busao.controller;

import br.com.ca.vou_de_busao.model.Passageiro;
import br.com.ca.vou_de_busao.service.PassageiroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@WebMvcTest(PassageiroController.class)
public class PassageiroControllerTest {
    @MockitoBean
    private PassageiroService passageiroService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void deveCriarPassageiroComSucesso() throws Exception {
        Passageiro passageiro = new Passageiro();
        passageiro.setNome("João da Silva");
        passageiro.setCpf("538.711.060-11");
        passageiro.setTelefone("999999999");

        Mockito.when(passageiroService.criar(Mockito.any(Passageiro.class)))
                .thenReturn(passageiro);

        mockMvc.perform(post("/passageiros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(passageiro)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("João da Silva"));
    }
}
