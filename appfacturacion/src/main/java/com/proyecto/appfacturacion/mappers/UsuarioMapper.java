package com.proyecto.appfacturacion.mappers;

import com.proyecto.appfacturacion.dto.UsuarioDTO;
import com.proyecto.appfacturacion.entities.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO toDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombre(usuario.getNombre())
                .apePaterno(usuario.getApePaterno())
                .apeMaterno(usuario.getApeMaterno())
                .email(usuario.getEmail())
                .telefono(usuario.getTelefono())
                .contrasena((usuario.getContrasena()))
                .build();
    }

    public static Usuario toEntity(UsuarioDTO dto) {
        return Usuario.builder()
                .idUsuario(dto.getIdUsuario())
                .nombre(dto.getNombre())
                .apePaterno(dto.getApePaterno())
                .apeMaterno(dto.getApeMaterno())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .contrasena(dto.getContrasena())
                .build();
    }
}
