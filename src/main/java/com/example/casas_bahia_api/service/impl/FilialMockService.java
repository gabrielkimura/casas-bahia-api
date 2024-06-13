package com.example.casas_bahia_api.service.impl;

import com.example.casas_bahia_api.dto.FilialDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FilialMockService {

    public List<FilialDTO> listarFiliais() {
        List<FilialDTO> filiais = new ArrayList<>();

        // Simulação de dados de filiais
        FilialDTO filial1 = new FilialDTO();
        filial1.setId(1L);
        filial1.setNome("Filial A");
        filial1.setCnpj("12345678000100");
        filial1.setCidade("São Paulo");
        filial1.setUf("SP");
        filial1.setTipo("Matriz");
        filial1.setAtivo(true);
        filial1.setDataCadastro(new Date());
        filial1.setUltimaAtualizacao(new Date());

        FilialDTO filial2 = new FilialDTO();
        filial2.setId(2L);
        filial2.setNome("Filial B");
        filial2.setCnpj("98765432000199");
        filial2.setCidade("Rio de Janeiro");
        filial2.setUf("RJ");
        filial2.setTipo("Filial");
        filial2.setAtivo(true);
        filial2.setDataCadastro(new Date());
        filial2.setUltimaAtualizacao(new Date());

        filiais.add(filial1);
        filiais.add(filial2);

        return filiais;
    }

    public FilialDTO buscarFilialPorId(Long id) {
        // Simulação de busca por ID
        List<FilialDTO> filiais = listarFiliais();
        return filiais.stream()
                .filter(filial -> filial.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
