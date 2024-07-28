package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.AlunoModel;
import br.edu.ifs.apinewsigaa.repository.AlunoRepository;
import br.edu.ifs.apinewsigaa.rest.dto.AlunoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //O serviço somente deve acessar outros serviços ou sua classe de repositório
public class AlunoService { //Classe de serviço é uma classe onde possui regras de negócio

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ModelMapper modelMapper;

    /*
     * Obtém um aluno pelo número de matrícula.
     *
     * @param matricula O número de matrícula do aluno a ser obtido.
     * @return Um objeto AlunoDto correspondente ao aluno encontrado.
     * @throws ObjectNotFoundException Caso o aluno não seja encontrado no banco de dados.
     */
    public AlunoDto ObterPorMatricula(String matricula) {
        Optional<AlunoModel> alunoOptional = alunoRepository.findByMatricula(matricula);
        AlunoModel alunoModel = alunoOptional.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Matrícula não encontrada! Matrícula: " + matricula));

        return modelMapper.map(alunoModel, AlunoDto.class); //SETTA o dado que está em alunoModel para o DTO
    }
        /**
         * Obtém todos os alunos cadastrados.
         *
         * @return Uma lista de objetos AlunoDto correspondentes aos alunos cadastrados.
         */

        public List<AlunoDto> ObterTodos() {
            List<AlunoModel> alunoList = alunoRepository.findAll();
            return alunoList.stream().map(aluno -> modelMapper.map(aluno, AlunoDto.class)).collect(Collectors.toList());
        }
        /*
        MANEIRA MAIS BRAÇAL
        Dever de casa: Encapsular o model de aluno para DTO para alterar o retorno do metodo
        AlunoModel.toDTO() (this para AlunoDTO)

        AlunoDto alunoDto = new AlunoDto();

        alunoDto.setMatricula(alunoModel.getMatricula());
        alunoDto.setNome(alunoModel.getNome());
        alunoDto.setApelido(alunoModel.getApelido());
        alunoDto.setEmail(alunoModel.getEmail());
        alunoDto.setCpf(alunoModel.getCpf());
        alunoDto.setCelular(alunoModel.getCelular());
        alunoDto.setDataNascimento(alunoModel.getDataNascimento());

        return alunoDto;
        */
}
