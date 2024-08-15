package br.edu.ifs.apinewsigaa.rest.dto;

import br.edu.ifs.apinewsigaa.model.projection.DisciplinaProfessorProjection;
import br.edu.ifs.apinewsigaa.repository.ProfessorRepository;
import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class ProfessorDtoPlus {
    @Column(name = "nome", length = 255, nullable = false)
    private String nome;
    @Column(name = "celular", length = 11, nullable = false, unique = true)
    private String celular;
    @Column(name = "matricula", nullable = false, unique = true)
    private int matricula;
    @Column
    private List<DisciplinaDto> disciplinasProfessor;
}
