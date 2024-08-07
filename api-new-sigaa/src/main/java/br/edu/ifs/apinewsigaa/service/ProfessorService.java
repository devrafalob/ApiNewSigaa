package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.ProfessorModel;
import br.edu.ifs.apinewsigaa.repository.ProfessorRepository;
import br.edu.ifs.apinewsigaa.rest.dto.ProfessorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public List<ProfessorDto> ObterTodos(){
        List<ProfessorModel> professorList = professorRepository.findAll();
        return professorList.stream().map(professor -> professor.toDto()).collect(Collectors.toList());
    }

    public ProfessorDto ObterPorMatricula(String matricula){
        Optional<ProfessorModel> professorOptional = professorRepository.findByMatricula(matricula);
        ProfessorModel professorModel = professorOptional.orElseThrow(() ->
                new ObjectNotFoundException("Erro:Matricula não encontrada! Matricula"+matricula));
        return professorModel.toDto();
    }

    public ProfessorDto ObterPorCpf(String cpf){
        Optional<ProfessorModel> professorOptional = professorRepository.findByCpf(cpf);
        ProfessorModel professorModel = professorOptional.orElseThrow(() ->
                new ObjectNotFoundException("Erro:Cpf não encontrado! Cpf"+cpf));
        return professorModel.toDto();
    }

    public ProfessorDto ObterPorCelular(String celular){
        Optional<ProfessorModel> professorOptional = professorRepository.findByCelular(celular);
        ProfessorModel professorModel = professorOptional.orElseThrow(() ->
                new ObjectNotFoundException("Erro:Celular não encontrado"+celular));
        return professorModel.toDto();
    }

    @Transactional
    public ProfessorDto Salvar(ProfessorModel novoProfessor){
        professorRepository.existsByMatricula(novoProfessor.getMatricula());
        try{
            return professorRepository.save(novoProfessor).toDto();
        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao salvar o professor.");
        }
    }

    @Transactional
    public ProfessorDto Atualizar(ProfessorModel professorExistente){
        try {
            professorRepository.existsById(professorExistente.getId());
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Professor Não encontrado. Matricula procurada: "+professorExistente.getMatricula());
        }
        try {
            return professorRepository.save(professorExistente).toDto();
        }catch(DataIntegrityException e){
            throw new DataIntegrityException("Erro ao Atualizar o professor.");
        }
    }

}
