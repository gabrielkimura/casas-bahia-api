package com.example.casas_bahia_api.controller;

import com.example.casas_bahia_api.domain.model.Filial;
import com.example.casas_bahia_api.domain.model.Vendedor;
import com.example.casas_bahia_api.dto.FilialDTO;
import com.example.casas_bahia_api.dto.VendedorDTO;
import com.example.casas_bahia_api.service.VendedorService;
import com.example.casas_bahia_api.service.exception.FilialNotFoundException;
import com.example.casas_bahia_api.service.impl.FilialMockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/vendedor")
@RequiredArgsConstructor
@Tag(name = "Vendedor")
public class VendedorController {
    private final VendedorService vendedorService;

    private final FilialMockService filialMockService;

    @GetMapping("/{matricula}")
    public ResponseEntity<Vendedor> findByMatricula(@PathVariable String matricula){
        var vendedor = vendedorService.findByMatricula(matricula);
        return ResponseEntity.ok(vendedor);
    }

    @PostMapping
    public ResponseEntity<Vendedor> insert(@RequestBody VendedorDTO vendedorDTO){

        Vendedor vendedor = Vendedor.builder()
                .nome(vendedorDTO.getNome())
                .dataNascimento(vendedorDTO.getDataNascimento())
                .cpfCnpj(vendedorDTO.getCpfCnpj())
                .email(vendedorDTO.getEmail())
                .tipoContratacao(vendedorDTO.getTipoContratacao())
                .build();

        FilialDTO filialDTO = filialMockService.buscarFilialPorId(vendedorDTO.getFilialId());
        if (filialDTO == null) {
            throw new FilialNotFoundException("Filial não encontrada com ID: " + vendedorDTO.getFilialId());
        }

        Filial filial = Filial.builder()
                .id(filialDTO.getId())
                .nome(filialDTO.getNome())
                .cnpj(filialDTO.getCnpj())
                .cidade(filialDTO.getCidade())
                .uf(filialDTO.getUf())
                .tipo(filialDTO.getTipo())
                .ativo(filialDTO.isAtivo())
                .dataCadastro(filialDTO.getDataCadastro())
                .ultimaAtualizacao(filialDTO.getUltimaAtualizacao())
                .build();

        vendedor.setFilial(filial);

        vendedorService.create(vendedor);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{matricula}")
                .buildAndExpand(vendedor.getMatricula())
                .toUri();
        return ResponseEntity.created(location).body(vendedor);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Vendedor> updateVendedor(@RequestBody VendedorDTO vendedorDTO, @PathVariable String matricula){
        Vendedor updatedVendedor = Vendedor.builder()
                .nome(vendedorDTO.getNome())
                .dataNascimento(vendedorDTO.getDataNascimento())
                .cpfCnpj(vendedorDTO.getCpfCnpj())
                .email(vendedorDTO.getEmail())
                .tipoContratacao(vendedorDTO.getTipoContratacao())
                .build();

        Vendedor vendedor = vendedorService.update(matricula, updatedVendedor);

        return ResponseEntity.ok(vendedor);
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deleteByMatricula(@PathVariable String matricula) {
        vendedorService.deleteByMatricula(matricula);
        return ResponseEntity.noContent().build();
    }
}
