package com.gestion.clientes;

import com.gestion.clientes.exception.ResourceNotFoundException;
import com.gestion.clientes.model.Cliente;
import com.gestion.clientes.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClienteServiceTests {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private com.gestion.clientes.repository.ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
    }

    @Test
    void testGuardarYListarClientesService() {
        Cliente cliente = new Cliente(null, "Pedro", "Lopez", "pedro@example.com");
        Cliente guardado = clienteService.guardarCliente(cliente);

        assertNotNull(guardado.getId());
        assertEquals("Pedro", guardado.getNombre());

        List<Cliente> clientes = clienteService.listarTodosLosClientes();
        assertEquals(1, clientes.size());
        assertEquals("Pedro", clientes.get(0).getNombre());
    }

    @Test
    void testActualizarClienteService() {
        Cliente cliente = clienteService.guardarCliente(new Cliente(null, "Maria", "Diaz", "maria@example.com"));

        Cliente datosActualizados = new Cliente(null, "Maria Elena", "Diaz Cruz", "maria.elena@example.com");
        Cliente actualizado = clienteService.actualizarCliente(cliente.getId(), datosActualizados);

        assertEquals("Maria Elena", actualizado.getNombre());
        assertEquals("Diaz Cruz", actualizado.getApellido());
        assertEquals("maria.elena@example.com", actualizado.getEmail());
    }

    @Test
    void testEliminarClienteService() {
        Cliente cliente = clienteService.guardarCliente(new Cliente(null, "Lucas", "Rojas", "lucas@example.com"));
        Long id = cliente.getId();

        clienteService.eliminarCliente(id);

        assertThrows(ResourceNotFoundException.class, () -> clienteService.obtenerClientePorId(id));
    }
}
