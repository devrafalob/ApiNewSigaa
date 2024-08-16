package br.edu.ifs.apinewsigaa.rest.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class ProfessorDto {
    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "dataNascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "celular", length = 14, nullable = false, unique = true)
    private String celular;
}
