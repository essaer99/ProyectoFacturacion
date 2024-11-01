package com.proyecto.appfacturacion.services;

import com.proyecto.appfacturacion.dto.RegistrarUsuarioDTO;
import com.proyecto.appfacturacion.dto.UsuarioDTO;
import com.proyecto.appfacturacion.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    UsuarioDTO createUser(UsuarioDTO usuarioDTO);

    UsuarioDTO updateUser(UsuarioDTO usuarioDTO, Integer id);

    List<UsuarioDTO> getAllUsers();

    Optional<UsuarioDTO> getUserById(Integer id);

    void deleteUser(Integer id);

    void register(UsuarioDTO usuarioDTO);

    UsuarioDTO findByEmail(String email);

    UsuarioDTO authenticate(String email, String contrasena);

    void save(UsuarioDTO usuarioDTO);

    Usuario save(RegistrarUsuarioDTO registrarUsuarioDTO);
}
