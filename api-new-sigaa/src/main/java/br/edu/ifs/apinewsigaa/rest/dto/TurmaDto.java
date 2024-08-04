package br.edu.ifs.apinewsigaa.rest.dto;

import jakarta.persistence.Column;

import java.util.Date;

public class TurmaDto {
    @Column(name = "dataInicio", nullable = false)
    private Date dataInicio;
    @Column(name = "dataFim", nullable = false)
    private Date dataFim;
    @Column(name = "idProfessor", nullable = false)
    private int idProfessor;
    @Column(name = "idDisciplina", nullable = false)
    private int idDisciplina;
}
