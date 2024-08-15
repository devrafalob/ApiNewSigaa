package br.edu.ifs.apinewsigaa.rest.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class TurmaDto {
    @Column(name = "dataInicio", nullable = false)
    private Date dataInicio;
    @Column(name = "idProfessor", nullable = false)
    private int idProfessor;
    @Column(name = "idDisciplina", nullable = false)
    private int idDisciplina;
}
