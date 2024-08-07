package br.edu.ifs.apinewsigaa.rest.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class ProfessorDto {
    @Column(name = "nome", length = 255, nullable = false)
    private String nome;
    @Column(name = "celular", length = 14, nullable = false, unique = true)
    private String celular;
    @Column(name = "matricula", nullable = false, unique = true)
    private int matricula;
}
