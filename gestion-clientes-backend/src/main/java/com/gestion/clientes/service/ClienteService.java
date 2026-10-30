package com.gestion.clientes.service;

import com.gestion.clientes.model.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> listarTodosLosClientes();

    Cliente guardarCliente(Cliente cliente);

    Cliente obtenerClientePorId(Long id);

    Cliente actualizarCliente(Long id, Cliente detallesCliente);

    void eliminarCliente(Long id);
}
