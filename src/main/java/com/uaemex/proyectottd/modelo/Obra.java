package com.uaemex.proyectottd.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "obra")
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obra_id_seq")
    @SequenceGenerator(name = "obra_id_seq", sequenceName = "obra_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 200)
    private String ubicacion;

    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal presupuesto;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

}