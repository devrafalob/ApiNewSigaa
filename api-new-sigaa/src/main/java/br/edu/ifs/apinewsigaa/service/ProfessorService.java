package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.exception.InternalServerErrorException;
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

    @Transactional
    public ProfessorDto Salvar(ProfessorModel novoProfessor){
        if(!professorRepository.existsByMatricula(novoProfessor.validarCPF(novoProfessor.getCpf()))) {
            if(!professorRepository.existsByCpf(novoProfessor.getCpf())){
                professorRepository.existsByMatricula(novoProfessor.getMatricula());
                try {
                    return professorRepository.save(novoProfessor).toDto();
                } catch (DataIntegrityException e) {
                    throw new DataIntegrityException("Erro ao salvar o professor.");
                }
            }else{
                throw  new InternalServerErrorException("Erro: Já existe um professor com esse Cpf!");
            }
        }else{
            throw new InternalServerErrorException("Erro: Já existe um professor com essa matricula!");
        }
    }

    @Transactional
    public ProfessorDto Atualizar(ProfessorModel professorExistente){
        if(professorRepository.existsById(professorExistente.getId())) {
            try {
                return professorRepository.save(professorExistente).toDto();
            } catch (DataIntegrityException e) {
                throw new DataIntegrityException("Erro ao Atualizar o professor.");
            }
        }else{
            throw new ObjectNotFoundException("Erro: Professor Não encontrado. Id procurado: " + professorExistente.getMatricula());
        }
    }

    @Transactional
    public void deletar(int id) {
        if (professorRepository.existsById(id)) {
            try {
                professorRepository.deleteById(id);
            } catch (DataIntegrityException e) {
                throw new DataIntegrityException("Erro: Não foi possível deletar o Professor.");
            }
        } else {
            throw new ObjectNotFoundException("Erro: Professor não encontrado. Id procurado: " + id);
        }
    }
}
