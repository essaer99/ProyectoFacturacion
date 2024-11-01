package com.proyecto.appfacturacion.controllers;


import com.proyecto.appfacturacion.dto.DatosFiscalesDTO;
import com.proyecto.appfacturacion.services.DatosFiscalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/datos-fiscales")
public class DatosFiscalesController {

    private final DatosFiscalesService datosFiscalesService;

    @Autowired
    public DatosFiscalesController(DatosFiscalesService datosFiscalesService){
        this.datosFiscalesService = datosFiscalesService;
    }

    @PostMapping
    public ResponseEntity<DatosFiscalesDTO> createDatosFiscales(@RequestBody DatosFiscalesDTO datosFiscalesDTO) {
        DatosFiscalesDTO savedDatosFiscales = datosFiscalesService.save(datosFiscalesDTO);
        return new ResponseEntity<>(savedDatosFiscales, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DatosFiscalesDTO>> getAllDatosFiscales() {
        List<DatosFiscalesDTO> datosFiscalesList = datosFiscalesService.findAll();
        return new ResponseEntity<>(datosFiscalesList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosFiscalesDTO> getDatosFiscalesById(@PathVariable int id) {
        return datosFiscalesService.findById(id)
                .map(datosFiscalesDTO -> new ResponseEntity<>(datosFiscalesDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosFiscalesDTO> updateDatosFiscales(@PathVariable int id, @RequestBody DatosFiscalesDTO datosFiscalesDTO) {
        try {
            DatosFiscalesDTO updatedDatosFiscales = datosFiscalesService.update(id, datosFiscalesDTO);
            return new ResponseEntity<>(updatedDatosFiscales, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDatosFiscales(@PathVariable int id) {
        datosFiscalesService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

