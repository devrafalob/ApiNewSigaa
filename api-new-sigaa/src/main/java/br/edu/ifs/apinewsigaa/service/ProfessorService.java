package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.ProfessorModel;
import br.edu.ifs.apinewsigaa.repository.ProfessorRepository;
import br.edu.ifs.apinewsigaa.rest.dto.ProfessorDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ModelMapper modelMapper;


    public List<ProfessorDto> ObterTodos(){
        List<ProfessorModel> professorList = professorRepository.findAll();
        return professorList.stream().map(professor -> modelMapper.map(professor, ProfessorDto.class)).collect(Collectors.toList());
    }

    public ProfessorDto ObterPorMatricula(String matricula){
        Optional<ProfessorModel> professorOptional = professorRepository.findByMatricula(matricula);
        ProfessorModel professorModel = professorOptional.orElseThrow(() ->
                new ObjectNotFoundException("Erro:Matricula não encontrada! Matricula"+matricula));
        return modelMapper.map(professorModel, ProfessorDto.class); //Converte o tipo de dados
    }

    public ProfessorDto ObterPorCpf(String cpf){
        Optional<ProfessorModel> professorOptional = professorRepository.findByCpf(cpf);
        ProfessorModel professorModel = professorOptional.orElseThrow(() ->
                new ObjectNotFoundException("Erro:Cpf não encontrado! Cpf"+cpf));
        return modelMapper.map(professorModel,ProfessorDto.class);
    }

    public ProfessorDto ObterPorCelular(String celular){
        Optional<ProfessorModel> professorOptional = professorRepository.findByCelular(celular);
        ProfessorModel professorModel = professorOptional.orElseThrow(() ->
                new ObjectNotFoundException("Erro:Celular não encontrado"+celular));
        return modelMapper.map(professorModel, ProfessorDto.class);
    }

}
