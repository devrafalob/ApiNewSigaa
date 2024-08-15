package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.model.DisciplinaModel;
import br.edu.ifs.apinewsigaa.rest.dto.DisciplinaDto;
import br.edu.ifs.apinewsigaa.service.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {
    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<DisciplinaDto>> findAll(){
        List<DisciplinaDto> disciplinaDtoList = disciplinaService.ObterTodos();
        return ResponseEntity.ok(disciplinaDtoList);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<DisciplinaDto> ObterPorNome(@PathVariable("nome") String nome){
        DisciplinaDto disciplinaDto = disciplinaService.ObterPorNome(nome);
        return ResponseEntity.ok(disciplinaDto);
    }

    @PostMapping("/Criar")
    public ResponseEntity<DisciplinaDto> Salvar(@RequestBody @Valid DisciplinaModel novaDisciplina){
        disciplinaService.Salvar(novaDisciplina);
        return ResponseEntity.ok(novaDisciplina.toDto());
    }

    @PutMapping("/Atualizar")
    public ResponseEntity<DisciplinaDto> Atualizar(@RequestBody @Valid DisciplinaModel disciplinaExistente){
        disciplinaService.Atualizar(disciplinaExistente);
        return ResponseEntity.ok(disciplinaExistente.toDto());
    }

    @DeleteMapping("/Deletar")
    public void Deletar(@RequestBody @Valid int id){
        disciplinaService.Deletar(id);
    }
}
