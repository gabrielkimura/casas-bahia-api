package com.example.casas_bahia_api.service.impl;

import com.example.casas_bahia_api.domain.model.Vendedor;
import com.example.casas_bahia_api.domain.repository.VendedorRepository;
import com.example.casas_bahia_api.service.VendedorService;
import com.example.casas_bahia_api.service.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class VendedorServiceImpl implements VendedorService {
    private final VendedorRepository vendedorRepository;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");


    @Override
    public Vendedor findByMatricula(String matricula){
        Optional<Vendedor> vendedorOptional = vendedorRepository.findByMatricula(matricula);

        if (vendedorOptional.isPresent())
            return vendedorOptional.get();
        else
            throw new MatriculaNotFoundException("Vendedor não encontrado");
    }

    @Override
    public void create(Vendedor vendedor){
        if (vendedorRepository.findByMatricula(vendedor.getMatricula()).isPresent()){
            throw new MatriculaAlreadyExistsException("Matricula ja existente: " + vendedor.getMatricula());
        }

        if (validaTipoContratacao(vendedor.getTipoContratacao())){
            throw new TipoContratacaoInvalidaException("Tipo de contratação inválida!");
        }

        if (validaCpfCnpj(vendedor.getCpfCnpj(), vendedor.getTipoContratacao())){
            throw new CpfCnpjIvalidException("CPF ou CNPJ inválido!");
        }

        validateEmail(vendedor.getEmail());

        vendedor.setMatricula(generateMatricula(vendedor.getTipoContratacao()));

        vendedorRepository.save(vendedor);
    }

    @Override
    public Vendedor update(String matricula, Vendedor updatedVendedor) {
        Vendedor existingVendedor = vendedorRepository.findByMatricula(matricula)
                .orElseThrow(() -> new MatriculaNotFoundException("Vendedor não encontrado com matrícula: " + matricula));

        if (validaTipoContratacao(updatedVendedor.getTipoContratacao())){
            throw new TipoContratacaoInvalidaException("Tipo de contratação inválida!");
        }

        if (validaCpfCnpj(updatedVendedor.getCpfCnpj(), updatedVendedor.getTipoContratacao())){
            throw new CpfCnpjIvalidException("CPF ou CNPJ inválido!");
        }

        validateEmail(updatedVendedor.getEmail());

        existingVendedor.setNome(updatedVendedor.getNome());
        existingVendedor.setDataNascimento(updatedVendedor.getDataNascimento());
        existingVendedor.setCpfCnpj(updatedVendedor.getCpfCnpj());
        existingVendedor.setEmail(updatedVendedor.getEmail());
        existingVendedor.setTipoContratacao(updatedVendedor.getTipoContratacao());
        existingVendedor.setFilial(updatedVendedor.getFilial());

        return vendedorRepository.save(existingVendedor);
    }


    @Transactional
    public void deleteByMatricula(String matricula) {
        vendedorRepository.findByMatricula(matricula)
                .orElseThrow(() -> new MatriculaNotFoundException("Vendedor não encontrado com matrícula: " + matricula));

        vendedorRepository.deleteByMatricula(matricula);
    }

    private boolean validaTipoContratacao(String tipoContratacao){
        return !switch (tipoContratacao.toLowerCase()) {
            case "clt", "outsourcing", "pessoa juridica" -> true;
            default -> false;
        };
    }

    private boolean validaCpfCnpj(String cpfCnpj, String tipoContratacao){
        return !switch (tipoContratacao.toLowerCase()) {
            case "clt", "outsourcing" -> cpfCnpj.length() == 11;
            case "pessoa juridica" -> cpfCnpj.length() == 14;
            default -> false;
        };
    }

    private String generateMatricula(String tipoContratacao){
        String matricula;
        String complemento = switch (tipoContratacao.toLowerCase()) {
            case "clt" -> " - CLT";
            case "pessoa juridica" -> " - PJ";
            case "outsourcing" -> " - OUT";
            default -> "";
        };

        do {
            matricula = generateRandomNumberMatricula().concat(complemento);
        } while (vendedorRepository.findByMatricula(matricula).isPresent());

        return matricula;
    }

    private String generateRandomNumberMatricula() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000000);
        return String.format("%06d", randomNumber);
    }

    private void validateEmail(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException("Formato de e-mail inválido: " + email);
        }
    }



}
