package com.proyectos.tienda.service;

import com.proyectos.tienda.model.Cliente;
import com.proyectos.tienda.model.Producto;

import java.util.List;

public interface IProductoService {

    //Traer/Lectura/GET (Un producto)
    public Producto traerProducto(Long codigo_producto);

    //Traer/Lectura/GET (Todos los productos)
    public List<Producto> listaProductos();

    //Crear/Alta/POST
    public void crearProducto(Producto producto);

    //Eliminar/Baja/DELETE
    public void eliminarProducto(Long codigo_producto);

    //Editar/Modificación/LECTURA
    public void editarProducto(Long codigo_producto, String nuevo_nombre, String nueva_marca, Double nuevo_costo, Double nueva_cantidadDisponible);

    //Traer productos que cuya cantidad sea menor a 5
    public List<Producto> productosCantMenor5();


}
