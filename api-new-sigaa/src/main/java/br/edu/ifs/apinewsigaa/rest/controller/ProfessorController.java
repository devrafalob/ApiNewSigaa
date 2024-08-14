package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.model.ProfessorModel;
import br.edu.ifs.apinewsigaa.rest.dto.ProfessorDto;
import br.edu.ifs.apinewsigaa.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
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
    public ResponseEntity<ProfessorDto> ObterPorCpf(@PathVariable("cpf")String cpf){
        ProfessorDto professorDto = professorService.ObterPorCpf(cpf);
        return ResponseEntity.ok(professorDto);
    }

    @PostMapping("/Criar")
    public ResponseEntity<ProfessorDto> Salvar(@RequestBody @Valid ProfessorModel novoProfessor){
        professorService.Salvar(novoProfessor);
        return ResponseEntity.ok(novoProfessor.toDto());
    }

    @PutMapping("/Atualizar")
    public ResponseEntity<ProfessorDto> Atualizar(@RequestBody @Valid ProfessorModel professorExistente){
        professorService.Atualizar(professorExistente);
        return ResponseEntity.ok(professorExistente.toDto());
    }

    @DeleteMapping("/Deletar")
    public void deletarPorId(@RequestBody @Valid int id){
        professorService.deletar(id);
    }
}
