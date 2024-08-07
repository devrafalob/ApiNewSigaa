package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.AlunoModel;
import br.edu.ifs.apinewsigaa.repository.AlunoRepository;
import br.edu.ifs.apinewsigaa.rest.dto.AlunoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //O serviço somente deve acessar outros serviços ou sua classe de repositório
public class AlunoService { //Classe de serviço é uma classe onde possui regras de negócio

    @Autowired //Injeta dependencias automaticamente do repositório
    private AlunoRepository alunoRepository;

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
        return alunoModel.toDto();
    }

    public List<AlunoDto> ObterTodos() {
        List<AlunoModel> alunoList = alunoRepository.findAll();
        return alunoList.stream().map(aluno -> aluno.toDto())
                .collect(Collectors.toList());
    }

    @Transactional
    public AlunoDto salvar(AlunoModel novoAluno) throws Exception {
        if (!alunoRepository.existsByCpf(novoAluno.getCpf())) {
            try {
                return alunoRepository.save(novoAluno).toDto();
            } catch (DataIntegrityException e) {
                throw new DataIntegrityException("Erro ao criar um novo aluno.");
            }
        } else {
            throw new Exception("O aluno já existe! ");
        }
    }

    @Transactional
    public AlunoDto atualizar(AlunoModel alunoExistente) {
        if (alunoRepository.existsById(alunoExistente.getId())) {
            try {
                return alunoRepository.save(alunoExistente).toDto();
            } catch (DataIntegrityException e) {
                throw new DataIntegrityException("Erro ao atualizar um aluno.");
            }
        } else {
            throw new ObjectNotFoundException("Aluno não encontrado. Id do aluno: " + alunoExistente.getId());
        }
    }

    @Transactional
    public void deletar(int id) {
        if (alunoRepository.existsById(id)) {
            try {
                alunoRepository.deleteById(id);
            } catch (DataIntegrityException e) {
                throw new DataIntegrityException("Erro ao deletar um aluno.");
            }
        } else {
            throw new ObjectNotFoundException("Aluno não encontrado. Id do aluno: " + id);
        }
    }
}
