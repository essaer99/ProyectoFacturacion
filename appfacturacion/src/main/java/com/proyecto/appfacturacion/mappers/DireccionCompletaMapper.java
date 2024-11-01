package com.proyecto.appfacturacion.mappers;

import com.proyecto.appfacturacion.dto.DireccionCompletaDTO;
import com.proyecto.appfacturacion.entities.DireccionCompleta;

public class DireccionCompletaMapper {

    public static DireccionCompletaDTO toDTO(DireccionCompleta direccion) {
        return DireccionCompletaDTO.builder()
                .idDireccion(direccion.getIdDireccion())
                .estado(direccion.getEstado())
                .municipio(direccion.getMunicipio())
                .cp(direccion.getCp())
                .calle(direccion.getCalle())
                .numero(direccion.getNumero())
                .build();
    }

    public static DireccionCompleta toEntity(DireccionCompletaDTO dto) {
        DireccionCompleta direccion = new DireccionCompleta();
        direccion.setIdDireccion(dto.getIdDireccion());
        direccion.setEstado(dto.getEstado());
        direccion.setMunicipio(dto.getMunicipio());
        direccion.setCp(dto.getCp());
        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        return direccion;
    }
}
