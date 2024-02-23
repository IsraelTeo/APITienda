package com.proyectos.tienda.service;

import com.proyectos.tienda.model.Cliente;
import com.proyectos.tienda.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {
    @Autowired
    private IClienteRepository clienteRepo;

    @Override
    public Cliente traerCliente(Long id_cliente) {
        return clienteRepo.findById(id_cliente).orElse(null);
    }

    @Override
    public List<Cliente> listaClientes() {
        return clienteRepo.findAll();
    }

    @Override
    public void crearCliente(Cliente cliente) {
        clienteRepo.save(cliente);

    }

    @Override
    public void eliminarCliente(Long id_cliente) {
        clienteRepo.deleteById(id_cliente);
    }

    @Override
    public void editarCliente(Long id_cliente, String nuevo_nombre, String nuevo_apellido, String nuevo_dni) {
        Cliente cliente = this.traerCliente(id_cliente);
        cliente.setNombre(nuevo_nombre);
        cliente.setApellido(nuevo_apellido);
        cliente.setDni(nuevo_dni);
        this.crearCliente(cliente);

    }
}
