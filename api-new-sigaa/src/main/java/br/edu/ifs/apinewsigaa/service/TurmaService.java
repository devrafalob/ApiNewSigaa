package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.TurmaModel;
import br.edu.ifs.apinewsigaa.repository.TurmaRepository;
import br.edu.ifs.apinewsigaa.rest.dto.TurmaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurmaService {
    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    ProfessorService professorService;

    @Autowired
    DisciplinaService disciplinaService;

    /**
     * Obtém todas as turmas e as converte para DTOs.
     *
     * Este método consulta o repositório de turmas para obter uma lista de todas as turmas armazenadas no banco de dados.
     * Em seguida, converte cada instância de `TurmaModel` para um `TurmaDto` usando o método `toDto()` e coleta todos os DTOs em uma lista.
     *
     * @return Lista de objetos `TurmaDto` representando todas as turmas.
     */
    public List<TurmaDto> ObterTodos() {
        List<TurmaModel> TurmaList = turmaRepository.findAll();
        return TurmaList.stream().map(turma -> turma.toDto()).collect(Collectors.toList());
    }

    /**
     * Obtém uma turma com base no ID do professor associado.
     *
     * Este método procura uma turma associada ao ID do professor fornecido. Se a turma não for encontrada, lança uma exceção personalizada
     * com uma mensagem de erro indicando que o professor não foi encontrado.
     *
     * @param idProfessor ID do professor para o qual se deseja obter a turma.
     * @return Objeto `TurmaDto` representando a turma associada ao professor.
     * @throws ObjectNotFoundException Se a turma associada ao professor não for encontrada.
     */
    public TurmaDto obterPorIdProfessor(int idProfessor) {
        Optional<TurmaModel> turmaOptional = turmaRepository.findByIdProfessor(idProfessor);
        TurmaModel turmaModel = turmaOptional.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Matricula do professor não encontrada. Matricula: " + idProfessor));
        return turmaModel.toDto();
    }

    /**
     * Salva uma nova turma no banco de dados.
     *
     * Este método verifica se o professor e a disciplina associados à nova turma existem. Se ambos existirem, a turma é salva no repositório.
     * Caso contrário, lança uma exceção personalizada indicando qual dos elementos não foi encontrado.
     *
     * @param novaTurma Objeto `TurmaModel` representando a nova turma a ser salva.
     * @return Objeto `TurmaDto` representando a turma recém-criada.
     * @throws ObjectNotFoundException Se o professor ou a disciplina não forem encontrados.
     */
    @Transactional
    public TurmaDto Salvar(TurmaModel novaTurma) {
        if(professorService.existeProfessor(novaTurma.getId())) {
            if (disciplinaService.existeDisciplina(novaTurma.getIdDisciplina())) {
                return turmaRepository.save(novaTurma).toDto();
            } else {
                throw new ObjectNotFoundException("Erro: Disciplina não encontrada.");
            }
        } else {
            throw new ObjectNotFoundException("Erro: Professor não encontrado.");
        }
    }

    /**
     * Atualiza uma turma existente.
     *
     * Este método verifica se a turma a ser atualizada, o professor e a disciplina existem. Se todos existirem, atualiza a turma no repositório.
     * Caso contrário, lança exceções personalizadas indicando qual dos elementos não foi encontrado.
     *
     * @param turmaExistente Objeto `TurmaModel` representando a turma a ser atualizada.
     * @return Objeto `TurmaDto` representando a turma atualizada.
     * @throws ObjectNotFoundException Se a turma, professor ou disciplina não forem encontrados.
     */
    @Transactional
    public TurmaDto Atualizar(TurmaModel turmaExistente) {
        if(turmaRepository.existsById(turmaExistente.getId())) {
            if(professorService.existeProfessor(turmaExistente.getId())) {
                if(disciplinaService.existeDisciplina(turmaExistente.getIdDisciplina())) {
                    return turmaRepository.save(turmaExistente).toDto();
                } else {
                    throw new ObjectNotFoundException("Erro: Disciplina não encontrada.");
                }
            } else {
                throw new ObjectNotFoundException("Erro: Professor não encontrado");
            }
        } else {
            throw new ObjectNotFoundException("Erro: Turma não encontrada");
        }
    }

    /**
     * Deleta uma turma com base no ID fornecido.
     *
     * Este método tenta deletar a turma do repositório com o ID fornecido. Se ocorrer uma exceção de integridade de dados durante a exclusão,
     * uma exceção personalizada é lançada para indicar o problema.
     *
     * @param id ID da turma a ser deletada.
     * @throws DataIntegrityException Se houver um erro ao deletar a turma devido a problemas de integridade de dados.
     */
    @Transactional
    public void Deletar(int id) {
        try {
            turmaRepository.deleteById(id);
        } catch(DataIntegrityException e) {
            throw new DataIntegrityException("Erro ao deletar a turma.");
        }
    }

    public boolean existeTurmaPorIdDisciplina(int id){
        if(turmaRepository.existsByIdDisciplina(id)){
            return true;
        }else{
            return false;
        }
    }
}
