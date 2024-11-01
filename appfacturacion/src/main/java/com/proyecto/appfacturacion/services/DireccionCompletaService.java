package com.proyecto.appfacturacion.services;

import com.proyecto.appfacturacion.dto.DireccionCompletaDTO;

import java.util.List;
import java.util.Optional;

public interface DireccionCompletaService {

    DireccionCompletaDTO crear(DireccionCompletaDTO direccionCompletaDTO);
    List<DireccionCompletaDTO> listar();
    Optional<DireccionCompletaDTO> obtenerPorId(Integer id);
    void eliminar(Integer id);
}
