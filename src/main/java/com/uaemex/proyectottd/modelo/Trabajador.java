package com.uaemex.proyectottd.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "trabajador")
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trabajador_id_seq")
    @SequenceGenerator(name = "trabajador_id_seq", sequenceName = "trabajador_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido_1;

    @Column(nullable = false, length = 50)
    private String apellido_2;

    @Column(nullable = false, precision = 38, scale = 0)
    private int edad;

    @Column(nullable = false, length = 20)
    private String tipo;

    @Column(nullable = false)
    private int estado;

    @ManyToOne
    @JoinColumn(name = "obra_id")
    private Obra obra;
}
