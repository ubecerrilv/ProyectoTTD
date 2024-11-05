package com.uaemex.proyectottd.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "obra")
public class Obra {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    @Column(nullable = false, length = 100)
    private String nombre;

    @Getter
    @Setter
    @Column(nullable = false, length = 200)
    private String ubicacion;

    @Getter
    @Setter
    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal presupuesto;

    @Getter
    @Setter
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Getter
    @Setter
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

}