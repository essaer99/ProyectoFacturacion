package com.proyecto.appfacturacion.repositories;


import com.proyecto.appfacturacion.entities.SesionUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SesionUsuarioRepository extends JpaRepository<SesionUsuario, Integer> {
}
