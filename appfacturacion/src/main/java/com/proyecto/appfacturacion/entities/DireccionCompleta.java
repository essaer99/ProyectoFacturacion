package com.proyecto.appfacturacion.entities;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "direccion_completa")
public class DireccionCompleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccionc")
    private Integer idDireccion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private String municipio;

    private String cp;

    private String calle;

    private Integer numero;


}
