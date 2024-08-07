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

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    /**
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

    /**
     * Busca  todos os alunos cadastrados.
     *
     * @return Uma lista de Alunos.
     */
    public List<AlunoDto> ObterTodos() {
        List<AlunoModel> alunoList = alunoRepository.findAll();
        return alunoList.stream().map(aluno -> aluno.toDto())
                .collect(Collectors.toList());
    }

    /**
     * Post: Insere uma nova instância de aluno.
     *
     * @param novoAluno Os dados que serão armazenados do aluno.
     * @return Um objeto Alunodto correspondente ao aluno salvo.
     * @throws DataIntegrityException Erro na integridade dos dados recebidos e os necessários.
     * @throws Exception Evita a duplicidade de dados.
     */
    @Transactional
    public AlunoDto salvar(AlunoModel novoAluno) throws Exception {
        if (!alunoRepository.existsByCpf(novoAluno.validarCPF(novoAluno.getCpf())) & !alunoRepository.existsByMatricula(novoAluno.getMatricula())){
            try {
                return alunoRepository.save(novoAluno).toDto();
            } catch (DataIntegrityException e) {
                throw new DataIntegrityException("Erro: Não foi possível criar um novo aluno.");
            }
        } else {
            throw new Exception("Erro: O aluno já existe!");
        }
    }

    /**
     * Put: Atualiza ma instância existente de aluno.
     *
     * @param alunoExistente Recebe a instancia de um aluno já existente.
     * @return Um objeto Dto do aluno atualizado.
     * @throws DataIntegrityException Erro na integridade entre os dados recebidos e os necessários.
     * @throws ObjectNotFoundException Inexistência de um objeto.
     */
    @Transactional
    public AlunoDto atualizar(AlunoModel alunoExistente) {
        if (alunoRepository.existsById(alunoExistente.getId())) {
            try {
                return alunoRepository.save(alunoExistente).toDto();
            } catch (DataIntegrityException e) {
                throw new DataIntegrityException("Erro: Não foi possível atualizar o aluno.");
            }
        } else {
            throw new ObjectNotFoundException("Erro: Aluno não encontrado. Id aluno: " + alunoExistente.getId());
        }
    }

    /**
     * Delete: Deleta uma instância de um objeto.
     *
     * @param id Apaga a instância por meio de um id já existente.
     * @throws DataIntegrityException Erro na integridade entre os dados recebidos e os necessários.
     * @throws ObjectNotFoundException Inexistência de um objeto.
     */
    @Transactional
    public void deletar(int id) {
        if (alunoRepository.existsById(id)) {
            try {
                alunoRepository.deleteById(id);
            } catch (DataIntegrityException e) {
                throw new DataIntegrityException("Erro: Não foi possível deletar o aluno.");
            }
        } else {
            throw new ObjectNotFoundException("Erro: Aluno não encontrado. Id aluno: " + id);
        }
    }
}
