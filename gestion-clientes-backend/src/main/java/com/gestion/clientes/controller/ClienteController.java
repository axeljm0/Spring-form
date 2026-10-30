package com.gestion.clientes.controller;

import com.gestion.clientes.model.Cliente;
import com.gestion.clientes.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/clientes", "/api/v1/clientes"})
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // GET /clientes (listar)
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarTodosLosClientes();
    }

    // POST /clientes (guardar)
    @PostMapping
    public Cliente guardarCliente(@RequestBody Cliente cliente) {
        return clienteService.guardarCliente(cliente);
    }

    // GET /clientes/{id} (obtener uno)
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    // PUT /clientes/{id} (actualizar)
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente detallesCliente) {
        Cliente clienteActualizado = clienteService.actualizarCliente(id, detallesCliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    // DELETE /clientes/{id} (eliminar)
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
