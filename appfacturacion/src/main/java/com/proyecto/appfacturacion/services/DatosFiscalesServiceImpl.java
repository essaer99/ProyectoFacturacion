package com.proyecto.appfacturacion.services;


import com.proyecto.appfacturacion.dto.DatosFiscalesDTO;
import com.proyecto.appfacturacion.entities.DatosFiscales;
import com.proyecto.appfacturacion.entities.DireccionCompleta;
import com.proyecto.appfacturacion.entities.Usuario;
import com.proyecto.appfacturacion.mappers.DatosFiscalesMapper;
import com.proyecto.appfacturacion.repositories.DatosFiscalesRepository;
import com.proyecto.appfacturacion.repositories.DireccionCompletaRepository;
import com.proyecto.appfacturacion.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DatosFiscalesServiceImpl implements DatosFiscalesService {

    @Autowired
    private final DatosFiscalesRepository datosFiscalesRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DireccionCompletaRepository direccionCompletaRepository;


    public DatosFiscalesServiceImpl(DatosFiscalesRepository datosFiscalesRepository) {
        this.datosFiscalesRepository = datosFiscalesRepository;
    }


    @Override
    public DatosFiscalesDTO save(DatosFiscalesDTO datosFiscalesDTO) {
        // Validar existencia de usuario
        if (datosFiscalesDTO.getUsuarioDaFis() == null) {
            throw new RuntimeException("El ID de usuario no puede ser nulo.");
        }

        Usuario usuario = usuarioRepository.findById(datosFiscalesDTO.getUsuarioDaFis())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        // Validar existencia de dirección
        if (datosFiscalesDTO.getDireccionCompleta() == null) {
            throw new RuntimeException("El ID de dirección no puede ser nulo.");
        }

        DireccionCompleta direccionCompleta = direccionCompletaRepository.findById(datosFiscalesDTO.getDireccionCompleta())
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada."));

        // Crear entidad DatosFiscales
        DatosFiscales datosFiscales = DatosFiscalesMapper.toEntity(datosFiscalesDTO, usuario, direccionCompleta);

        // Guardar en la base de datos
        DatosFiscales savedDatosFiscales = datosFiscalesRepository.save(datosFiscales);
        return DatosFiscalesMapper.toDTO(savedDatosFiscales);
    }

    @Override
    public List<DatosFiscalesDTO> findAll() {
        return datosFiscalesRepository.findAll().stream()
                .map(DatosFiscalesMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DatosFiscalesDTO> findById(int id) {
        return datosFiscalesRepository.findById(id)
                .map(DatosFiscalesMapper::toDTO);
    }

    @Override
    public DatosFiscalesDTO update(int id, DatosFiscalesDTO datosFiscalesDTO) {
        return datosFiscalesRepository.findById(id)
                .map(existingDatos -> {
                    existingDatos.setRfc(datosFiscalesDTO.getRfc());
                    existingDatos.setRazonSocial(datosFiscalesDTO.getRazonSocial());
                    existingDatos.setTipoContribuyente(datosFiscalesDTO.getTipoContribuyente());
                    existingDatos.setRegimenFiscal(datosFiscalesDTO.getRegimenFiscal());
                    DatosFiscales updatedDatosFiscales = datosFiscalesRepository.save(existingDatos);
                    return DatosFiscalesMapper.toDTO(updatedDatosFiscales);
                })
                .orElseThrow(() -> new IllegalArgumentException("DatosFiscales not found with id: " + id));
    }

    @Override
    public void deleteById(int id) {
        datosFiscalesRepository.deleteById(id);
    }
}
