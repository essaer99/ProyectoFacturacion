package com.proyecto.appfacturacion.mappers;

import com.proyecto.appfacturacion.dto.RegistrarUsuarioDTO;
import com.proyecto.appfacturacion.entities.DatosFiscales;
import com.proyecto.appfacturacion.entities.DireccionCompleta;
import com.proyecto.appfacturacion.entities.Usuario;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class RegistrarUsuarioMapper {

    public static Usuario toEntity(RegistrarUsuarioDTO dto) {
        DireccionCompleta direccion = new DireccionCompleta();
        direccion.setEstado(dto.getEstado());
        direccion.setMunicipio(dto.getMunicipio());
        direccion.setCp(dto.getCodigoPostal());
        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());

        DatosFiscales datosFiscales = new DatosFiscales();
        datosFiscales.setRfc(dto.getRfc());
        datosFiscales.setRazonSocial(dto.getRazonSocial());
        datosFiscales.setTipoContribuyente(dto.getTipoContribuyente());
        datosFiscales.setRegimenFiscal(dto.getRegimenFiscal());

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApePaterno(dto.getApellidoPaterno());
        usuario.setApeMaterno(dto.getApellidoMaterno());
        usuario.setTelefono(dto.getTelefono());
        usuario.setEmail(dto.getEmail());
        usuario.setContrasena(dto.getContrasena());

        // Establecer relaciones
        usuario.setDatosFiscales(Set.of(datosFiscales)); // Establecer el conjunto de DatosFiscales

        // Establecer la relación inversa
        datosFiscales.setUsuarioDaFis(usuario);
        usuario.setDatosFiscales(Set.of(datosFiscales)); // También puedes establecer la dirección aquí

        return usuario;
    }
}
