package com.example.casas_bahia_api.domain.repository;

import com.example.casas_bahia_api.domain.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    Optional<Vendedor> findByMatricula(String matricula);


    void deleteByMatricula(String matricula);
}
