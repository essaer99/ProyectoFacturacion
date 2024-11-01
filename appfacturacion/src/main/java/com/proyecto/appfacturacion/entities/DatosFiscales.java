package com.proyecto.appfacturacion.entities;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "datos_fiscales")
public class DatosFiscales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_datos_fiscales")
    private int idDatosFiscales;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioDaFis;

    @ManyToOne
    @JoinColumn(name = "id_direccionc")
    private DireccionCompleta direccionCompleta;

    private String rfc;

    @Column(name = "razon_social")
    private String razonSocial;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contribuyente")
    private TipoContribuyente tipoContribuyente;

    @Enumerated(EnumType.STRING)
    @Column(name = "regimen_fiscal")
    private RegimenFiscal regimenFiscal;





}
