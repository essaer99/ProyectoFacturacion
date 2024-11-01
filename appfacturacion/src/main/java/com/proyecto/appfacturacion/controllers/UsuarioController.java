package com.proyecto.appfacturacion.controllers;


import com.proyecto.appfacturacion.dto.RegistrarUsuarioDTO;
import com.proyecto.appfacturacion.dto.UsuarioDTO;
import com.proyecto.appfacturacion.entities.Usuario;
import com.proyecto.appfacturacion.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUser(@RequestBody UsuarioDTO usuarioDTO) {
        try{
            UsuarioDTO createdUser = usuarioService.createUser(usuarioDTO);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsers() {
        return new ResponseEntity<>(usuarioService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public UsuarioDTO updateUser(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.updateUser(usuarioDTO, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUserById(@PathVariable Integer id) {
        Optional<UsuarioDTO> usuarioDTO = usuarioService.getUserById(id);
        return usuarioDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        Optional<UsuarioDTO> usuarioDTO = usuarioService.getUserById(id);
        if (usuarioDTO.isPresent()) {
            usuarioService.deleteUser(usuarioDTO.get().getIdUsuario());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody RegistrarUsuarioDTO registrarUsuarioDTO) {
        try {
            // Llamar al servicio para guardar el usuario
            Usuario nuevoUsuario = usuarioService.save(registrarUsuarioDTO);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED); // Retorna 201 Created
        } catch (RuntimeException e) {
            // Manejo de excepciones (puedes personalizarlo)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Retorna 400 Bad Request
        }
    }
}
