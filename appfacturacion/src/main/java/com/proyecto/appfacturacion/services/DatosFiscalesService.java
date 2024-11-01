package com.proyecto.appfacturacion.services;


import com.proyecto.appfacturacion.dto.DatosFiscalesDTO;

import java.util.List;
import java.util.Optional;

public interface DatosFiscalesService {

    DatosFiscalesDTO save(DatosFiscalesDTO datosFiscalesDTO);

    List<DatosFiscalesDTO> findAll();

    Optional<DatosFiscalesDTO> findById(int id);

    DatosFiscalesDTO update(int id, DatosFiscalesDTO datosFiscalesDTO);

    void deleteById(int id);

}