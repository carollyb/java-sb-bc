package com.example.bootcampibm.controller;

import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.dto.ClienteDTO;
import com.example.bootcampibm.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    public void setup() {
        ClienteDTO clienteDTO = new ClienteDTO(1, "maria", "maria@email.com");
    }

    @Test
    public void testInsertCliente() throws Exception{
        Cliente clienteInserido = new Cliente(1, "maria", "maria@email.com");
        Mockito.when(clienteService.insert(any(Cliente.class))).thenReturn(clienteInserido);

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"nome\":\"maria\",\"email\":\"maria@email.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("location", "http://localhost/clientes/1"));

    }

    @Test
    public void testDeleteCliente() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testFindClienteByIdExistente() throws Exception {
        Integer idCliente = 1;
        Cliente clienteEncontrado = new Cliente(1, "maria", "maria@email.com");

        Mockito.when(clienteService.find(idCliente)).thenReturn(clienteEncontrado);

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("maria"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("maria@email.com"));
    }
}
