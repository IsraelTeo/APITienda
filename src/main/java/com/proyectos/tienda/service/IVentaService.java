package com.proyectos.tienda.service;

import com.proyectos.tienda.dto.VentaProductoClienteDTO;
import com.proyectos.tienda.model.Cliente;
import com.proyectos.tienda.model.Producto;
import com.proyectos.tienda.model.Venta;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {

    //Traer/Lectura/GET (Una venta)
    public Venta traerVenta(Long codigo_venta);

    //Traer/Lectura/GET (Todas las ventas)
    public List<Venta> listaVentas();

    //Crear/Alta/POST
    public void crearVenta(Venta venta);

    //Eliminar/Baja/DELETE
    public void eliminarVenta(Long codigo_venta);

    //Editar/Modificación/LECTURA
    public void editarVenta(Long codigo_venta, LocalDate nueva_fechaVenta, Double nuevo_total, List<Producto> nueva_listaProductos, Cliente unCliente);

    //Traer lista de productos de una determinada venta
    public List<Producto> productosDeUnaVenta(Long codigo_venta);

    //Calcular sumatoria de montos de las ventas de un determinado un día
    public Double montoVentasDia(LocalDate fecha_venta);

    //Calcular cantidad total de ventas de un determinado día
    public int ventasTotalesDia(LocalDate fecha_venta);

    //Calcular monto mas alto de la lista de ventas
    public Venta montoAltoListaVentas();

    //Obtener el codigo_venta, el total de ventas, cant de productos, nombre_cliente,apellido_cliente de la venta con el monto más alto
    public VentaProductoClienteDTO ventaMontoMasAlto();



}
