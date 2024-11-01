package com.proyecto.appfacturacion.controllers;


import com.proyecto.appfacturacion.dto.DireccionCompletaDTO;
import com.proyecto.appfacturacion.services.DireccionCompletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direccion")
public class DireccionCompletaController {

    @Autowired
    DireccionCompletaService direccionCompletaService;

    @PostMapping
    public ResponseEntity<DireccionCompletaDTO> crearDireccion(@RequestBody DireccionCompletaDTO direccionCompletaDTO) {
        DireccionCompletaDTO nuevaDireccion = direccionCompletaService.crear(direccionCompletaDTO);
        return ResponseEntity.status(201).body(nuevaDireccion);
    }

    @GetMapping
    public ResponseEntity<List<DireccionCompletaDTO>> listarDirecciones() {
        List<DireccionCompletaDTO> direcciones = direccionCompletaService.listar();
        return ResponseEntity.ok(direcciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionCompletaDTO> obtenerDireccion(@PathVariable Integer id) {
        return direccionCompletaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDireccion(@PathVariable Integer id) {
        direccionCompletaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
