package com.example.casas_bahia_api.service;

import com.example.casas_bahia_api.domain.model.Vendedor;

public interface VendedorService {

    Vendedor findByMatricula(String matricula);

    void create(Vendedor vendedor);

    Vendedor update(String matricula, Vendedor updatedVendedor);

    void deleteByMatricula(String matricula);
}
