package com.example.casas_bahia_api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FilialDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private String cidade;
    private String uf;
    private String tipo;
    private boolean ativo;
    private Date dataCadastro;
    private Date ultimaAtualizacao;
}
