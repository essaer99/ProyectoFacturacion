package com.proyecto.appfacturacion.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String nombre;
    private String apePaterno;
    private String apeMaterno;
    private String email;
    private String telefono;
    private String contrasena;
}
