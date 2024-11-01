package com.proyecto.appfacturacion.repositories;

import com.proyecto.appfacturacion.entities.DireccionCompleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DireccionCompletaRepository extends JpaRepository<DireccionCompleta, Integer> {
}
