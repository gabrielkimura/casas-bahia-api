package com.example.casas_bahia_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class VendedorDTO {
    @NotEmpty(message = "O nome é obrigatório")
    @Schema(description = "Nome do vendedor", example = "João Silva")
    private String nome;

    @Schema(description = "Data de nascimento do vendedor", type = "string", pattern = "dd/MM/yyyy", example = "15/03/1985")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @NotEmpty(message = "O CPF ou CNPJ é obrigatório")
    @Schema(description = "CPF ou CNPJ do vendedor", example = "12345678901")
    private String cpfCnpj;

    @NotEmpty(message = "O e-mail é obrigatório")
    @Schema(description = "Email do vendedor", example = "joao.silva@example.com")
    private String email;

    @NotEmpty(message = "O tipo de contratação é obrigatório")
    @Schema(description = "Tipo de contratação do vendedor", example = "CLT")
    private String tipoContratacao;

    @NotEmpty(message = "O ID da filial é obrigatório")
    @Schema(description = "ID da filial associada", example = "1")
    private Long filialId;
}
