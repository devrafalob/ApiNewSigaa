package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import br.edu.ifs.apinewsigaa.rest.dto.AlunoDto;
import br.edu.ifs.apinewsigaa.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@Valid
@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    /**
     * Obtém todos os alunos cadastrados.
     *
     * @return Uma lista de {@link AlunoDto} representando todos os alunos.
     */
    @GetMapping
    public ResponseEntity<List<AlunoDto>> findAll() {
        List<AlunoDto> alunoDtoList = alunoService.ObterTodos();
        return ResponseEntity.ok(alunoDtoList);
    }

    /**
     * Obtém um aluno pelo número de matrícula.
     *
     * @param matricula Número da matrícula do aluno a ser recuperado.
     * @return O AlunoDto correspondente à matrícula fornecida.
     */
    @GetMapping("/{matricula}")
    public ResponseEntity<AlunoDto> ObterPorMatricula(@PathVariable("matricula") String matricula) {
        AlunoDto alunoDto = alunoService.ObterPorMatricula(matricula);
        return ResponseEntity.ok(alunoDto);
    }

    /**
     * Cria um novo aluno com base nos dados fornecidos.
     *
     * @param novoAluno Dados do aluno a ser criado, no formato AlunoModel.
     * @return O AlunoDto do aluno criado.
     */
    @PostMapping("/Criar")
    public ResponseEntity<AlunoDto> Salvar(@RequestBody @Valid AlunoModel novoAluno) throws Exception {
        AlunoDto alunoDto = alunoService.salvar(novoAluno);
        return ResponseEntity.ok(novoAluno.toDto());
    }

    /**
     * Atualiza os dados de um aluno existente.
     *
     * @param alunoExistente Dados atualizados do aluno, no formato AlunoModel.
     * @return O AlunoDto do aluno atualizado.
     */
    @PutMapping("/Atualizar")
    public ResponseEntity<AlunoDto> Atualizar(@RequestBody @Valid AlunoModel alunoExistente) {
        AlunoDto alunoDto = alunoService.atualizar(alunoExistente);
        return ResponseEntity.ok(alunoExistente.toDto());
    }

    /**
     * Deleta um aluno pelo ID fornecido.
     *
     * @param id ID do aluno a ser deletado.
     */
    @DeleteMapping("/Deletar")
    public void deletarPorId(@RequestBody @Valid int id) {
        alunoService.deletar(id);
    }
}
