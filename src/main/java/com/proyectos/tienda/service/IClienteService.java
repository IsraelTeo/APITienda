package com.proyectos.tienda.service;

import com.proyectos.tienda.model.Cliente;
import com.proyectos.tienda.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IClienteService {

    //Traer/Lectura/GET (Un cliente)
    public Cliente traerCliente(Long idd_cliente);

    //Traer/Lectura/GET (Todos los clientes)
    public List<Cliente> listaClientes();

    //Crear/Alta/POST
    public void crearCliente(Cliente cliente);

    //Eliminar/Baja/DELETE
    public void eliminarCliente(Long id_cliente);

    //Editar/Modificación/LECTURA
    public void editarCliente(Long id_cliente, String nuevo_nombre, String nuevo_apellido, String nuevo_dni);




}
