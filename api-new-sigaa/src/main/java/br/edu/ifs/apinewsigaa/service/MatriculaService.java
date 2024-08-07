package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.MatriculaModel;
import br.edu.ifs.apinewsigaa.repository.MatriculaRepository;
import br.edu.ifs.apinewsigaa.rest.dto.MatriculaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatriculaService {
    @Autowired
    private MatriculaRepository matriculaRepository;

    public MatriculaDto obterPorIdTurma(int idTurma){
        Optional<MatriculaModel> matriculaOptional = matriculaRepository.findByIdTurma(idTurma);
        MatriculaModel matriculaModel = matriculaOptional.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Id da turma não encontrado"+idTurma));
        return matriculaModel.toDto();
    }

    public MatriculaDto obterPorIdAluno(int idAluno){
        Optional<MatriculaModel> matriculaOptional = matriculaRepository.findByIdAluno(idAluno);
        MatriculaModel matriculaModel = matriculaOptional.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Matricula do aluno não encontrada. Matricula"+ idAluno));
        return matriculaModel.toDto();
    }
}
