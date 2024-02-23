package com.proyectos.tienda.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VentaProductoClienteDTO implements Serializable {
    private Long codigo_venta;
    private Double total;
    private Double cantidad;
    private String nombre;
    private String apellido;
}
