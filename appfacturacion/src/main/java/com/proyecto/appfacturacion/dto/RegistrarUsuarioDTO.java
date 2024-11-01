package com.proyecto.appfacturacion.dto;


import com.proyecto.appfacturacion.entities.Estado;
import com.proyecto.appfacturacion.entities.RegimenFiscal;
import com.proyecto.appfacturacion.entities.TipoContribuyente;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrarUsuarioDTO {

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String rfc;
    private String razonSocial;
    private TipoContribuyente tipoContribuyente;
    private RegimenFiscal regimenFiscal;
    private Estado estado;
    private String municipio;
    private String codigoPostal;
    private String calle;
    private Integer numero;
    private String email;
    private String contrasena;
}
