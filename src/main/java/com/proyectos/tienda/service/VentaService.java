package com.proyectos.tienda.service;

import com.proyectos.tienda.dto.VentaProductoClienteDTO;
import com.proyectos.tienda.model.Cliente;
import com.proyectos.tienda.model.Producto;
import com.proyectos.tienda.model.Venta;
import com.proyectos.tienda.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;

@Service
public class VentaService implements IVentaService{
    @Autowired
    private IVentaRepository ventaRepo;

    @Override
    public Venta traerVenta(Long codigo_venta) {
        return ventaRepo.findById(codigo_venta).orElse(null);
    }

    @Override
    public List<Venta> listaVentas() {
        return ventaRepo.findAll();
    }

    @Override
    public void crearVenta(Venta venta) {
        ventaRepo.save(venta);
    }

    @Override
    public void eliminarVenta(Long codigo_venta) {
        ventaRepo.deleteById(codigo_venta);
    }

    @Override
    public void editarVenta(Long codigo_venta, LocalDate nueva_fechaVenta, Double nuevo_total, List<Producto> nueva_listaProductos, Cliente nuevo_cliente) {
        Venta venta = this.traerVenta(codigo_venta);
        venta.setFecha_venta(nueva_fechaVenta);
        venta.setTotal(nuevo_total);
        venta.setListaProductos(nueva_listaProductos);
        venta.setUnCliente(nuevo_cliente);
        this.crearVenta(venta);
    }

    @Override
    public List<Producto> productosDeUnaVenta(Long codigo_venta) {
        this.listaVentas();
        for(Venta venta: listaVentas()) {
            if (venta.getCodigo_venta() == codigo_venta) {
                return venta.getListaProductos();
            }
        }
        return null;
    }

    @Override
    public Double montoVentasDia(LocalDate fecha_venta) {
        Double montoAcumular = 0.0;
        for(Venta venta: this.listaVentas()){
            if(venta.getFecha_venta()==fecha_venta){
                montoAcumular = montoAcumular + venta.getTotal();
                return montoAcumular;
            }
        }
        return montoAcumular;
    }

    @Override
    public int ventasTotalesDia(LocalDate fecha_venta) {
        int montoContar = 0;
        for (Venta venta : this.listaVentas()) {
            if (venta.getFecha_venta() == fecha_venta) {
                montoContar = montoContar + 1;
            }
            return montoContar;
        }
        return montoContar;
    }

    @Override
    public Venta montoAltoListaVentas() {
        Venta ventaMontoAlto = null;
        double montoMayor = 0.0;

        for (Venta venta : this.listaVentas()) {
            if (venta.getTotal() > montoMayor) {
                montoMayor = venta.getTotal();
                ventaMontoAlto = venta;
            }
        }
        return ventaMontoAlto;
    }

    @Override
    public VentaProductoClienteDTO ventaMontoMasAlto() {
        Venta ventaMontoAlto = this.montoAltoListaVentas();
        if (ventaMontoAlto != null) {
            VentaProductoClienteDTO ventProductClientDTO = new VentaProductoClienteDTO();
            ventProductClientDTO.setCodigo_venta(ventaMontoAlto.getCodigo_venta());
            ventProductClientDTO.setTotal(ventaMontoAlto.getTotal());
            ventProductClientDTO.setCantidad((double) ventaMontoAlto.getListaProductos().size());
            ventProductClientDTO.setNombre(ventaMontoAlto.getUnCliente().getNombre());
            ventProductClientDTO.setApellido(ventaMontoAlto.getUnCliente().getApellido());
            return ventProductClientDTO;
        } else {
            return null;
        }
    }
}
