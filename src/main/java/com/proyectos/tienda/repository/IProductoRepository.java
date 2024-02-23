package com.proyectos.tienda.repository;

import com.proyectos.tienda.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto,Long> {
}
