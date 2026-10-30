package com.gestion.clientes.service.impl;

import com.gestion.clientes.exception.ResourceNotFoundException;
import com.gestion.clientes.model.Cliente;
import com.gestion.clientes.repository.ClienteRepository;
import com.gestion.clientes.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> listarTodosLosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El cliente con el ID " + id + " no existe."));
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente detallesCliente) {
        Cliente cliente = obtenerClientePorId(id);

        cliente.setNombre(detallesCliente.getNombre());
        cliente.setApellido(detallesCliente.getApellido());
        cliente.setEmail(detallesCliente.getEmail());

        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(Long id) {
        Cliente cliente = obtenerClientePorId(id);
        clienteRepository.delete(cliente);
    }
}
