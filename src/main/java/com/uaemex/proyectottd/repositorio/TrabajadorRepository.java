package com.uaemex.proyectottd.repositorio;

import com.uaemex.proyectottd.modelo.Obra;
import com.uaemex.proyectottd.modelo.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {
    @Query("SELECT t FROM Trabajador t WHERE t.obra.id = NULL")
    List<Trabajador> findTrabajadorDisponible();


}
