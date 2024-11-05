package com.uaemex.proyectottd.repositorio;

import com.uaemex.proyectottd.modelo.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Integer> {
}
