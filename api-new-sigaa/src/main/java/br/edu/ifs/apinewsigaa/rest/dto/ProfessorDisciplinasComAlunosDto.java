package br.edu.ifs.apinewsigaa.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProfessorDisciplinasComAlunosDto {
    private ProfessorDto professor;
    private List<DisciplinaComAlunosDto> disciplinas;

}
