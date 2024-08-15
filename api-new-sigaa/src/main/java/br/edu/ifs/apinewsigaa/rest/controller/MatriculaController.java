package br.edu.ifs.apinewsigaa.rest.controller;


import br.edu.ifs.apinewsigaa.model.MatriculaModel;
import br.edu.ifs.apinewsigaa.rest.dto.MatriculaDto;
import br.edu.ifs.apinewsigaa.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {
    @Autowired
    MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<MatriculaDto>> FindAll(){
        List<MatriculaDto> matriculaDtoList = matriculaService.ObterTodos();
        return ResponseEntity.ok(matriculaDtoList);
    }

    @GetMapping("/idTurma")
    public ResponseEntity<MatriculaDto> ObterPorIdTurma(@PathVariable("idTurma")int idTurma){
        MatriculaDto matriculaDto = matriculaService.obterPorIdAluno(idTurma);
        return ResponseEntity.ok(matriculaDto);
    }

    @GetMapping("/idAluno")
    public ResponseEntity<MatriculaDto> ObterPorIdAluno(@PathVariable("idAluno")int idAluno){
        MatriculaDto matriculaDto = matriculaService.obterPorIdAluno(idAluno);
        return ResponseEntity.ok(matriculaDto);
    }

    @PostMapping("/Criar")
    public ResponseEntity<MatriculaDto> Criar(@RequestBody @Valid MatriculaModel novaMatricula){
        matriculaService.Salvar(novaMatricula);
        return ResponseEntity.ok(novaMatricula.toDto());
    }

    @PutMapping("/Atualizar")
    public ResponseEntity<MatriculaDto> Atualizar(@RequestBody @Valid MatriculaModel matriculaExistente){
        matriculaService.Atualizar(matriculaExistente);
        return ResponseEntity.ok(matriculaExistente.toDto());
    }

    @DeleteMapping("/Deletar")
    public void Deletar(@RequestBody @Valid int id){
        matriculaService.Deletar(id);
    }
}
