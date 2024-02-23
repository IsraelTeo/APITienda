package com.proyectos.tienda.controller;

import com.proyectos.tienda.model.Cliente;
import com.proyectos.tienda.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteServ;

    @PostMapping("/crear")
    public ResponseEntity<String> crearCliente(@RequestBody Cliente cliente){
        clienteServ.crearCliente(cliente);
        return new ResponseEntity<>("El cliente fue creado correctamente. ", HttpStatus.CREATED);
    }

    @GetMapping("/")
    @ResponseBody
    public List<Cliente> traerListaClientes(){
        return clienteServ.listaClientes();
    }

    @GetMapping("/{id_cliente}")
    @ResponseBody
    public Cliente traerCliente(@PathVariable Long id_cliente){
        return clienteServ.traerCliente(id_cliente);
    }

    @DeleteMapping("/eliminar/{id_cliente}")
    public ResponseEntity<String> eliminarCliente (@PathVariable Long id_cliente){
        clienteServ.eliminarCliente(id_cliente);
        return new ResponseEntity<>("El cliente fue eliminado correctamente", HttpStatus.OK);
    }

    @PutMapping("/editar/{id_cliente}")
    @ResponseBody
    public Cliente editarCliente(@PathVariable Long id_cliente,
                                 @RequestParam(required = false, name = "nombre") String nuevo_nombre,
                                 @RequestParam(required = false, name = "apellido") String nuevo_apellido,
                                 @RequestParam(required = false, name = "dni") String nuevo_dni){
        clienteServ.editarCliente(id_cliente, nuevo_nombre, nuevo_apellido, nuevo_dni);
        Cliente cliente = clienteServ.traerCliente(id_cliente);
        return cliente;
    }
}

