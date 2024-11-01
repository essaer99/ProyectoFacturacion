package com.proyecto.appfacturacion.dto;


import com.proyecto.appfacturacion.entities.DatosFiscales;
import com.proyecto.appfacturacion.entities.RegimenFiscal;
import com.proyecto.appfacturacion.entities.TipoContribuyente;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DatosFiscalesDTO {

    private int idDatosFiscales;
    private Integer usuarioDaFis;
    private Integer direccionCompleta;
    private String rfc;
    private String razonSocial;
    private TipoContribuyente tipoContribuyente;
    private RegimenFiscal regimenFiscal;
}
