package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.rest.dto.ProfessorDto;
import br.edu.ifs.apinewsigaa.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorDto>> FindAll(){
        List<ProfessorDto> professorDtoList = professorService.ObterTodos();
        return ResponseEntity.ok(professorDtoList);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<ProfessorDto> ObterPorMatricula(@PathVariable("matricula")String matricula){
        ProfessorDto professorDto = professorService.ObterPorMatricula(matricula);
        return ResponseEntity.ok(professorDto);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ProfessorDto> ObterPorCpf(@PathVariable("matricula")String cpf){
        ProfessorDto professorDto = professorService.ObterPorCpf(cpf);
        return ResponseEntity.ok(professorDto);
    }

    @GetMapping("/{celular")
    public ResponseEntity<ProfessorDto> ObterPorCelular(@PathVariable("celular")String celular){
        ProfessorDto professorDto = professorService.ObterPorCelular(celular);
        return ResponseEntity.ok(professorDto);
    }
}
