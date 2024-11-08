package com.uaemex.proyectottd.repositorio;

import com.uaemex.proyectottd.modelo.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Integer> {
    @Query("SELECT o FROM Obra o WHERE o.fechaFin > CURRENT_DATE")
    List<Obra> findObrasConFechaFinAntesDeHoy();
}
