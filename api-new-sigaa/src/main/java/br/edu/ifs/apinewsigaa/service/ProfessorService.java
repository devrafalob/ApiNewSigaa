package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.exception.InternalServerErrorException;
import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.ProfessorModel;
import br.edu.ifs.apinewsigaa.model.projection.DisciplinaProfessorProjection;
import br.edu.ifs.apinewsigaa.repository.ProfessorRepository;
import br.edu.ifs.apinewsigaa.rest.dto.ProfessorDto;
import br.edu.ifs.apinewsigaa.rest.dto.ProfessorDtoPlus;
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

    /**
     * Obtém todos os professores e os converte para DTOs.
     *
     * Este método consulta o repositório de professores para obter uma lista de todos os professores armazenados no banco de dados.
     * Em seguida, converte cada instância de `ProfessorModel` para um `ProfessorDto` usando o método `toDto()` e coleta todos os DTOs em uma lista.
     *
     * @return Lista de objetos `ProfessorDto` representando todos os professores.
     */
    public List<ProfessorDto> ObterTodos() {
        List<ProfessorModel> professorList = professorRepository.findAll();
        return professorList.stream().map(professor -> professor.toDto()).collect(Collectors.toList());
    }

    /**
     * Obtém um professor com base na matrícula fornecida.
     *
     * Este método procura um professor associado à matrícula fornecida. Se o professor não for encontrado, lança uma exceção personalizada
     * com uma mensagem de erro indicando que a matrícula não foi encontrada.
     *
     * @param matricula Matrícula do professor que se deseja obter.
     * @return Objeto `ProfessorDto` representando o professor associado à matrícula.
     * @throws ObjectNotFoundException Se o professor associado à matrícula não for encontrado.
     */
    public ProfessorDto ObterPorMatricula(String matricula) {
        Optional<ProfessorModel> professorOptional = professorRepository.findByMatricula(matricula);
        ProfessorModel professorModel = professorOptional.orElseThrow(() ->
                new ObjectNotFoundException("Erro: Matrícula não encontrada! Matrícula " + matricula));
        return professorModel.toDto();
    }

    /**
     * Obtém um professor com base no CPF fornecido.
     *
     * Este método procura um professor associado ao CPF fornecido. Se o professor não for encontrado, lança uma exceção personalizada
     * com uma mensagem de erro indicando que o CPF não foi encontrado.
     *
     * @param cpf CPF do professor que se deseja obter.
     * @return Objeto `ProfessorDto` representando o professor associado ao CPF.
     * @throws ObjectNotFoundException Se o professor associado ao CPF não for encontrado.
     */
    public ProfessorDto ObterPorCpf(String cpf) {
        Optional<ProfessorModel> professorOptional = professorRepository.findByCpf(cpf);
        ProfessorModel professorModel = professorOptional.orElseThrow(() ->
                new ObjectNotFoundException("Erro: CPF não encontrado! CPF " + cpf));
        return professorModel.toDto();
    }

    /**
     * Salva um novo professor no banco de dados.
     *
     * Este método verifica se já existe um professor com a mesma matrícula ou CPF antes de salvar o novo professor. Se não existir, o professor
     * é salvo no repositório. Caso contrário, lança exceções indicando que já existe um professor com a matrícula ou CPF fornecido.
     *
     * @param novoProfessor Objeto `ProfessorModel` representando o novo professor a ser salvo.
     * @return Objeto `ProfessorDto` representando o professor recém-criado.
     * @throws InternalServerErrorException Se já existir um professor com a matrícula ou CPF fornecido.
     * @throws DataIntegrityException Se ocorrer um erro de integridade de dados ao salvar o professor.
     */
    @Transactional
    public ProfessorDto Salvar(ProfessorModel novoProfessor) {
        if(!professorRepository.existsByMatricula(novoProfessor.validarCPF(novoProfessor.getCpf()))) {
            if(!professorRepository.existsByCpf(novoProfessor.getCpf())) {
                professorRepository.existsByMatricula(novoProfessor.getMatricula());
                try {
                    return professorRepository.save(novoProfessor).toDto();
                } catch (DataIntegrityException e) {
                    throw new DataIntegrityException("Erro ao salvar o professor.");
                }
            } else {
                throw new InternalServerErrorException("Erro: Já existe um professor com esse CPF!");
            }
        } else {
            throw new InternalServerErrorException("Erro: Já existe um professor com essa matrícula!");
        }
    }

    /**
     * Atualiza um professor existente.
     *
     * Este método verifica se o professor a ser atualizado existe no repositório. Se existir, o professor é atualizado no banco de dados.
     * Caso contrário, lança uma exceção indicando que o professor não foi encontrado. Além disso, trata exceções de integridade de dados.
     *
     * @param professorExistente Objeto `ProfessorModel` representando o professor a ser atualizado.
     * @return Objeto `ProfessorDto` representando o professor atualizado.
     * @throws ObjectNotFoundException Se o professor não for encontrado.
     * @throws DataIntegrityException Se ocorrer um erro de integridade de dados ao atualizar o professor.
     */
    @Transactional
    public ProfessorDto Atualizar(ProfessorModel professorExistente) {
        if(professorRepository.existsById(professorExistente.getId())) {
            try {
                return professorRepository.save(professorExistente).toDto();
            } catch (DataIntegrityException e) {
                throw new DataIntegrityException("Erro ao atualizar o professor.");
            }
        } else {
            throw new ObjectNotFoundException("Erro: Professor não encontrado. Id procurado: " + professorExistente.getMatricula());
        }
    }

    /**
     * Deleta um professor com base no ID fornecido.
     *
     * Este método tenta deletar o professor do repositório com o ID fornecido. Se ocorrer um erro de integridade de dados durante a exclusão,
     * uma exceção personalizada é lançada para indicar o problema. Caso o professor não seja encontrado, também lança uma exceção personalizada.
     *
     * @param id ID do professor a ser deletado.
     * @throws ObjectNotFoundException Se o professor não for encontrado.
     * @throws DataIntegrityException Se ocorrer um erro de integridade de dados ao deletar o professor.
     */
    @Transactional
    public void deletar(int id) {
        if (professorRepository.existsById(id)) {
            try {
                professorRepository.deleteById(id);
            } catch (DataIntegrityException e) {
                throw new DataIntegrityException("Erro: Não foi possível deletar o professor.");
            }
        } else {
            throw new ObjectNotFoundException("Erro: Professor não encontrado. Id procurado: " + id);
        }
    }

    /**
     * Obtém todas as disciplinas associadas a um professor com base na matrícula fornecida.
     *
     * Este método consulta o repositório para obter uma lista de disciplinas associadas ao professor com a matrícula fornecida.
     *
     * @param matricula Matrícula do professor para o qual se deseja obter as disciplinas.
     * @return Lista de objetos `DisciplinaProfessorProjection` representando as disciplinas associadas ao professor.
     */
    public List<DisciplinaProfessorProjection> obterDisciplinasPorMatricula(String matricula) {
        return professorRepository.ObterDisciplinasProfessor(matricula);
    }

}
