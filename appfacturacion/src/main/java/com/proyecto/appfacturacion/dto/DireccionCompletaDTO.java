package com.proyecto.appfacturacion.dto;


import com.proyecto.appfacturacion.entities.DireccionCompleta;
import com.proyecto.appfacturacion.entities.Estado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DireccionCompletaDTO {

    private Integer idDireccion;
    private Estado estado;
    private String municipio;
    private String cp;
    private String calle;
    private Integer numero;
}
