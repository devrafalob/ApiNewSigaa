package br.edu.ifs.apinewsigaa.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProfessorDisciplinasDto {
    private ProfessorDto professor;
    private List<DisciplinaDto> disciplinas;
}
