package com.proyecto.appfacturacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private Integer idUsuario;
    private String nombre;
    private String apePaterno;
    private String apeMaterno;
    private String email;
    private String telefono;
    private String contrasena;
}
