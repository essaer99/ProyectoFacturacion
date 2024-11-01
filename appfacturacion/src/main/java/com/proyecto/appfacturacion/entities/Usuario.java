package com.proyecto.appfacturacion.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    private String nombre;

    @Column(name = "apellido_paterno")
    private String apePaterno;

    @Column(name = "apellido_materno")
    private String apeMaterno;

    private String email;

    private String telefono;

    private String contrasena;

    @OneToMany(mappedBy = "usuarioDaFis", cascade = CascadeType.ALL)
    private Set<DatosFiscales> datosFiscales;
}
