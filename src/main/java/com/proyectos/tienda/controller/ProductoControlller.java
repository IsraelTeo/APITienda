package com.proyectos.tienda.controller;

import com.proyectos.tienda.model.Cliente;
import com.proyectos.tienda.model.Producto;
import com.proyectos.tienda.service.IClienteService;
import com.proyectos.tienda.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoControlller {
    @Autowired
    private IProductoService productServ;

    @PostMapping("/crear")
    public ResponseEntity<String> crearProducto(@RequestBody Producto producto){
        productServ.crearProducto(producto);
        return new ResponseEntity<>("El producto fue creado correctamente. ", HttpStatus.CREATED);
    }

    @GetMapping("/")
    @ResponseBody
    public List<Producto> traerListaProductos(){
        return productServ.listaProductos();
    }

    @GetMapping("/{codigo_producto}")
    @ResponseBody
    public Producto traerProdducto(@PathVariable Long codigo_producto){
        return productServ.traerProducto(codigo_producto);
    }

    @DeleteMapping("/eliminar/{codigo_producto}")
    public ResponseEntity<String> eliminarProducto (@PathVariable Long codigo_producto){
        productServ.eliminarProducto(codigo_producto);
        return new ResponseEntity<>("El producto fue eliminado correctamente", HttpStatus.OK);
    }

    @PutMapping("/editar/{codigo_producto}")
    @ResponseBody
    public Producto editarProducto(@PathVariable Long codigo_producto,
                                 @RequestParam(required = false, name = "nombre") String nuevo_nombre,
                                 @RequestParam(required = false, name = "marca") String nueva_marca,
                                 @RequestParam(required = false, name = "costo") Double nuevo_costo, @RequestParam(required = false, name = "cantidad_disponible") Double cantidad_disponible){
        productServ.editarProducto(codigo_producto, nuevo_nombre, nueva_marca, nuevo_costo, cantidad_disponible);
        Producto producto = productServ.traerProducto(codigo_producto);
        return producto;
    }

    @GetMapping("/falta_stock")
    public List<Producto> traerListaProductosMenor5(){
        return productServ.productosCantMenor5();
    }
}
