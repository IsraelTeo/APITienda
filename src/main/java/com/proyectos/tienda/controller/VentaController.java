package com.proyectos.tienda.controller;

import com.proyectos.tienda.dto.VentaProductoClienteDTO;
import com.proyectos.tienda.model.Cliente;
import com.proyectos.tienda.model.Producto;
import com.proyectos.tienda.model.Venta;
import com.proyectos.tienda.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private IVentaService ventServ;

    @PostMapping("/crear")
    public ResponseEntity<String> crearVenta(@RequestBody Venta venta){
        ventServ.crearVenta(venta);
        return new ResponseEntity<>("¡¡La venta fue realizada con exito!! ", HttpStatus.CREATED);
    }

    @GetMapping("/")
    @ResponseBody
    public List<Venta> traerListaVentas(){
        return ventServ.listaVentas();
    }

    @GetMapping("/traer/{codigo_venta}")
    @ResponseBody
    public Venta traerVenta(@PathVariable Long codigo_venta){
        return ventServ.traerVenta(codigo_venta);
    }

    @DeleteMapping("/eliminar/{codigo_venta}")
    public ResponseEntity<String> eliminarVenta (@PathVariable Long codigo_venta){
        ventServ.eliminarVenta(codigo_venta);
        return new ResponseEntity<>("La venta fue eliminada correctamente", HttpStatus.OK);
    }

    @PutMapping("/editar/{codigo_venta}")
    @ResponseBody
    public Venta editarVenta(@PathVariable Long codigo_venta, @RequestParam(required = false, name = "fecha") LocalDate nueva_fechaVenta,
                                 @RequestParam(required = false, name = "total") Double nuevo_total,
                                 @RequestParam(required = false, name = "listaProductos") List <Producto> nueva_listaProductos,
                                 @RequestParam(required = false, name = "cliente") Cliente nuevo_cliente){
        ventServ.editarVenta(codigo_venta, nueva_fechaVenta, nuevo_total, nueva_listaProductos, nuevo_cliente);
        Venta venta = ventServ.traerVenta(codigo_venta);
        return venta;
    }

    @GetMapping("/productos/{codigo_venta}")
    @ResponseBody
    public List<Producto> traerListaProductosDeUnaVenta(@PathVariable Long codigo_venta){
        return ventServ.productosDeUnaVenta(codigo_venta);
    }

    @GetMapping("/{fecha_venta}")
    public ResponseEntity<String> sumatoriaMontoYCantTotalDeUnDia(@PathVariable LocalDate fecha_venta){
        ventServ.montoVentasDia(fecha_venta);
        ventServ.ventasTotalesDia(fecha_venta);
        return new ResponseEntity<>("Día: "+fecha_venta+", sumatoria del monto: "
                +ventServ.montoVentasDia(fecha_venta)+" y ventas totales: "
                +ventServ.ventasTotalesDia(fecha_venta), HttpStatus.OK);
    }

    @GetMapping("/mayor_venta")
    public ResponseEntity<String> datosVentaConMontoMasAlto(){
        Venta venta1 = ventServ.montoAltoListaVentas();
        VentaProductoClienteDTO venta2 = ventServ.ventaMontoMasAlto();
        return new ResponseEntity<>("La venta con el monto mas alto es: "+ventServ.ventaMontoMasAlto()+
                "y sus datos son: Código de venta: "+venta2.getCodigo_venta()+", total: "+venta2.getTotal()+
                ", cantidad de productos: "+venta2.getCantidad()+", nombre del cliente: "+venta2.getNombre()+
                " y el apellido del cliente es: "+venta2.getApellido(), HttpStatus.OK);
    }


}
