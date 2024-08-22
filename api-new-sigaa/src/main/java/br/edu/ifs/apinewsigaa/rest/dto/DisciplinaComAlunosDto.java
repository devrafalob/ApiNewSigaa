package br.edu.ifs.apinewsigaa.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class DisciplinaComAlunosDto {
    private DisciplinaDto disciplina;
    private List<AlunoDto> alunos;

}
