package com.proyecto.appfacturacion.services;

import com.proyecto.appfacturacion.dto.DireccionCompletaDTO;
import com.proyecto.appfacturacion.entities.DireccionCompleta;
import com.proyecto.appfacturacion.mappers.DireccionCompletaMapper;
import com.proyecto.appfacturacion.repositories.DireccionCompletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DireccionCompletaServiceImpl implements DireccionCompletaService{

    @Autowired
    private DireccionCompletaRepository direccionCompletaRepository;


    @Override
    public DireccionCompletaDTO crear(DireccionCompletaDTO direccionCompletaDTO) {
        DireccionCompleta direccionCompleta = DireccionCompletaMapper.toEntity(direccionCompletaDTO);
        DireccionCompleta nuevaDireccion = direccionCompletaRepository.save(direccionCompleta);
        return DireccionCompletaMapper.toDTO(nuevaDireccion);
    }

    @Override
    public List<DireccionCompletaDTO> listar() {
        return direccionCompletaRepository.findAll()
                .stream()
                .map(DireccionCompletaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DireccionCompletaDTO> obtenerPorId(Integer id) {
        Optional<DireccionCompleta> direccion = direccionCompletaRepository.findById(id);
        return direccion.map(DireccionCompletaMapper::toDTO);
    }

    @Override
    public void eliminar(Integer id) {
        direccionCompletaRepository.deleteById(id);
    }
}
