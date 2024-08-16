package br.edu.ifs.apinewsigaa.rest.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class MatriculaDto {
    @Column(name = "idTurma", nullable = false)
    private int idTurma;
    @Column(name = "idAluno", nullable = false)
    private int idAluno;
}
