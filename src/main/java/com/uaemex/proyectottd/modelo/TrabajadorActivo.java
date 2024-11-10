package com.uaemex.proyectottd.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "trabajadores_activos_v")
@Setter
@Getter
public class TrabajadorActivo {

    @Id
    @Column(name = "trabajador_id")
    private int trabajadorId;

    @Column(name = "trabajador_nombre")
    private String trabajadorNombre;

    @Column(name = "trabajador_apellido_1")
    private String trabajadorApellido1;

    @Column(name = "trabajador_apellido_2")
    private String trabajadorApellido2;

    @Column
    private int edad;

    @Column
    private String tipo;

    @Column(name = "obra_id")
    private int obraId;

    @Column(name = "obra_nombre")
    private String obraNombre;

    @Column(name = "obra_ubicacion")
    private String obraUbicacion;

    @Column(name = "obra_presupuesto")
    private BigDecimal obraPresupuesto;

    @Column(name = "obra_fecha_inicio")
    private LocalDate obraFechaInicio;

    @Column(name = "obra_fecha_fin")
    private LocalDate obraFechaFin;

    // Getters y setters
}

