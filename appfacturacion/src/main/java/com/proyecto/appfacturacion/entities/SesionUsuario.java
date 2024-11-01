package com.proyecto.appfacturacion.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="sesion_usuario")
public class SesionUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesion")
    private Integer idSesion;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario idSeUsuario;

    @Column(name = "token_jwt")
    private String tokenJwt;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "fecha_expiracion")
    private Date fechaExpiracion;


}
