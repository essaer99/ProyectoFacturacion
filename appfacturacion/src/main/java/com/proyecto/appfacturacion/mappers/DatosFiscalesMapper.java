package com.proyecto.appfacturacion.mappers;

import com.proyecto.appfacturacion.dto.DatosFiscalesDTO;
import com.proyecto.appfacturacion.entities.DatosFiscales;
import com.proyecto.appfacturacion.entities.DireccionCompleta;
import com.proyecto.appfacturacion.entities.Usuario;
import com.proyecto.appfacturacion.repositories.DireccionCompletaRepository;
import com.proyecto.appfacturacion.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DatosFiscalesMapper {

    @Autowired
    private static UsuarioRepository usuarioRepository;

    @Autowired
    private static DireccionCompletaRepository direccionCompletaRepository;

    public static DatosFiscalesDTO toDTO(DatosFiscales datosFiscales) {
        return DatosFiscalesDTO.builder()
                .idDatosFiscales(datosFiscales.getIdDatosFiscales())
                .usuarioDaFis(datosFiscales.getUsuarioDaFis().getIdUsuario())
                .direccionCompleta(datosFiscales.getDireccionCompleta().getIdDireccion())
                .rfc(datosFiscales.getRfc())
                .razonSocial(datosFiscales.getRazonSocial())
                .tipoContribuyente(datosFiscales.getTipoContribuyente())
                .regimenFiscal(datosFiscales.getRegimenFiscal())
                .build();
    }

    public static DatosFiscales toEntity(DatosFiscalesDTO dto, Usuario usuario, DireccionCompleta direccionCompleta) {
        return DatosFiscales.builder()
                .idDatosFiscales(dto.getIdDatosFiscales())
                .rfc(dto.getRfc())
                .razonSocial(dto.getRazonSocial())
                .tipoContribuyente(dto.getTipoContribuyente())
                .regimenFiscal(dto.getRegimenFiscal())
                .usuarioDaFis(usuario)  // Asegúrate de asignar el usuario
                .direccionCompleta(direccionCompleta)  // Asegúrate de asignar la dirección
                .build();
    }

}
