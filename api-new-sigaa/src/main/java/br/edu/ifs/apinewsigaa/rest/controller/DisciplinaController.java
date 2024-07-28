package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.rest.dto.DisciplinaDto;
import br.edu.ifs.apinewsigaa.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
