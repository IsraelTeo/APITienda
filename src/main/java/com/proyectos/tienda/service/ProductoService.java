package com.proyectos.tienda.service;

import com.proyectos.tienda.model.Producto;
import com.proyectos.tienda.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private IProductoRepository productRepo;

    @Override
    public Producto traerProducto(Long codigo_producto) {
        return productRepo.findById(codigo_producto).orElse(null);
    }

    @Override
    public List<Producto> listaProductos() {
        return productRepo.findAll();
    }

    @Override
    public void crearProducto(Producto producto) {
        productRepo.save(producto);
    }

    @Override
    public void eliminarProducto(Long codigo_producto) {
        productRepo.deleteById(codigo_producto);
    }

    @Override
    public void editarProducto(Long codigo_producto, String nuevo_nombre, String nueva_marca, Double nuevo_costo,Double nueva_cantidadDisponible) {
         Producto producto = this.traerProducto(codigo_producto);
         producto.setNombre(nuevo_nombre);
         producto.setMarca(nueva_marca);
         producto.setCosto(nuevo_costo);
         producto.setCantidad_disponible(nueva_cantidadDisponible);
        this.crearProducto(producto);
    }

    @Override
    public List<Producto> productosCantMenor5() {
        List<Producto> listaProductos = this.listaProductos();
        List<Producto> listaProductosCantMenor5 = new ArrayList<>();
        for(Producto producto: listaProductos){
            if(producto.getCantidad_disponible()<5){
                listaProductosCantMenor5.add(producto);
            }
        }
        return listaProductosCantMenor5;
    }
}
