package com.gestion.clientes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.clientes.model.Cliente;
import com.gestion.clientes.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
    }

    @Test
    void testGuardarYListarClientes() throws Exception {
        Cliente cliente = new Cliente(null, "Carlos", "Perez", "carlos@example.com");

        // POST /clientes
        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nombre", is("Carlos")))
                .andExpect(jsonPath("$.apellido", is("Perez")))
                .andExpect(jsonPath("$.email", is("carlos@example.com")));

        // GET /clientes
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Carlos")));
    }

    @Test
    void testObtenerYActualizarYEliminarCliente() throws Exception {
        // Guardar cliente inicial
        Cliente cliente = new Cliente(null, "Ana", "Gomez", "ana@example.com");
        Cliente guardado = clienteRepository.save(cliente);

        // GET /clientes/{id}
        mockMvc.perform(get("/clientes/" + guardado.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Ana")))
                .andExpect(jsonPath("$.apellido", is("Gomez")));

        // PUT /clientes/{id}
        Cliente actualizacion = new Cliente(null, "Ana Maria", "Gomez Silva", "anamaria@example.com");
        mockMvc.perform(put("/clientes/" + guardado.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actualizacion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Ana Maria")))
                .andExpect(jsonPath("$.email", is("anamaria@example.com")));

        // DELETE /clientes/{id}
        mockMvc.perform(delete("/clientes/" + guardado.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eliminar", is(true)));

        // Verificar que ya no existe (404)
        mockMvc.perform(get("/clientes/" + guardado.getId()))
                .andExpect(status().isNotFound());
    }
}
