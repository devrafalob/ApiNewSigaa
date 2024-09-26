package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.model.TurmaModel;
import br.edu.ifs.apinewsigaa.rest.dto.TurmaDto;
import br.edu.ifs.apinewsigaa.service.TurmaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turma")
@SecurityRequirement(name="Keycloak")
public class TurmaController {
    @Autowired
    TurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<TurmaDto>> ObterTodos(){
        List<TurmaDto> turmaDtoList = turmaService.ObterTodos();
        return ResponseEntity.ok(turmaDtoList);
    }

    @GetMapping("/idProfessor")
    public ResponseEntity<TurmaDto> ObterPorIdProfessor(@PathVariable("idProfessor") int idProfessor){
        TurmaDto turmaDto = turmaService.obterPorIdProfessor(idProfessor);
        return ResponseEntity.ok(turmaDto);
    }

    @PostMapping("/Criar")
    public ResponseEntity<TurmaDto> novaTurma(@RequestBody @Valid TurmaModel novaTurma){
        turmaService.Salvar(novaTurma);
        return ResponseEntity.ok(novaTurma.toDto());
    }

    @PutMapping ("/Atualizar")
    public ResponseEntity<TurmaDto> turmaExistente(@RequestBody @Valid TurmaModel turmaExistente){
        turmaService.Atualizar(turmaExistente);
        return ResponseEntity.ok(turmaExistente.toDto());
    }

    @DeleteMapping("/Deletar")
    public void Deletar(@RequestBody @Valid int id){
        turmaService.Deletar(id);
    }
}
