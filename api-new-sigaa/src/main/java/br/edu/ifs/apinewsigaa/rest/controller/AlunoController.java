package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import br.edu.ifs.apinewsigaa.rest.dto.AlunoDto;
import br.edu.ifs.apinewsigaa.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoDto>> findAll() {
        List<AlunoDto> alunoDtoList = alunoService.ObterTodos();
        return ResponseEntity.ok(alunoDtoList);
    }

    @GetMapping("/{matricula}") //Fará uma busca de acordo com a matrícula
    public ResponseEntity<AlunoDto> ObterPorMatricula(@PathVariable("matricula") String matricula) {
        AlunoDto alunoDto = alunoService.ObterPorMatricula(matricula);
        return ResponseEntity.ok(alunoDto);
    }

    @PostMapping("/Criar")
    public ResponseEntity<AlunoDto> Salvar(@RequestBody @Valid AlunoModel novoAluno) throws Exception {
        AlunoDto alunoDto = alunoService.salvar(novoAluno);
        return ResponseEntity.ok(novoAluno.toDto());
    }

    @PutMapping("/Atualizar")
    public ResponseEntity<AlunoDto> Atualizar(@RequestBody @Valid AlunoModel alunoExistente) {
        AlunoDto alunoDto = alunoService.atualizar(alunoExistente);
        return ResponseEntity.ok(alunoExistente.toDto());
    }

    @DeleteMapping("/Deletar")
    public void deletarPorId(@RequestBody @Valid int id) {
        alunoService.deletar(id);
    }
}
