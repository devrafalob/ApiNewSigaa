package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.MatriculaModel;
import br.edu.ifs.apinewsigaa.repository.MatriculaRepository;
import br.edu.ifs.apinewsigaa.rest.dto.MatriculaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatriculaService {
    @Autowired
    private MatriculaRepository matriculaRepository;

    /**
     * Obtém todas as matrículas e as converte para DTOs.
     *
     * Este método consulta o repositório de matrículas para obter uma lista de todas as matrículas armazenadas no banco de dados.
     * Em seguida, converte cada instância de `MatriculaModel` para um `MatriculaDto` usando o método `toDto()` e coleta todos os DTOs em uma lista.
     *
     * @return Lista de objetos `MatriculaDto` representando todas as matrículas.
     */
    public List<MatriculaDto> ObterTodos() {
        List<MatriculaModel> matriculaList = matriculaRepository.findAll();
        return matriculaList.stream().map(matricula -> matricula.toDto()).collect(Collectors.toList());
    }

    /**
     * Obtém uma matrícula com base no ID da turma associada.
     *
     * Este método procura uma matrícula associada ao ID da turma fornecida. Se a matrícula não for encontrada, lança uma exceção personalizada
     * com uma mensagem de erro indicando que o ID da turma não foi encontrado.
     *
     * @param idTurma ID da turma para a qual se deseja obter a matrícula.
     * @return Objeto `MatriculaDto` representando a matrícula associada à turma.
     * @throws ObjectNotFoundException Se a matrícula associada à turma não for encontrada.
     */
    public MatriculaDto obterPorIdTurma(int idTurma) {
        Optional<MatriculaModel> matriculaOptional = matriculaRepository.findByIdTurma(idTurma);
        MatriculaModel matriculaModel = matriculaOptional.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Id da turma não encontrado " + idTurma));
        return matriculaModel.toDto();
    }

    /**
     * Obtém uma matrícula com base no ID do aluno associado.
     *
     * Este método procura uma matrícula associada ao ID do aluno fornecido. Se a matrícula não for encontrada, lança uma exceção personalizada
     * com uma mensagem de erro indicando que a matrícula do aluno não foi encontrada.
     *
     * @param idAluno ID do aluno para o qual se deseja obter a matrícula.
     * @return Objeto `MatriculaDto` representando a matrícula associada ao aluno.
     * @throws ObjectNotFoundException Se a matrícula associada ao aluno não for encontrada.
     */
    public MatriculaDto obterPorIdAluno(int idAluno) {
        Optional<MatriculaModel> matriculaOptional = matriculaRepository.findByIdAluno(idAluno);
        MatriculaModel matriculaModel = matriculaOptional.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Matricula do aluno não encontrada. Matricula " + idAluno));
        return matriculaModel.toDto();
    }

    /**
     * Salva uma nova matrícula no banco de dados.
     *
     * Este método salva uma nova matrícula no repositório. A matrícula é persistida no banco de dados e retornada como um DTO.
     *
     * @param novaMatricula Objeto `MatriculaModel` representando a nova matrícula a ser salva.
     * @return Objeto `MatriculaDto` representando a matrícula recém-criada.
     */
    public MatriculaDto Salvar(MatriculaModel novaMatricula) {
        return matriculaRepository.save(novaMatricula).toDto();
    }

    /**
     * Atualiza uma matrícula existente.
     *
     * Este método atualiza uma matrícula existente no repositório. A matrícula é persistida no banco de dados e retornada como um DTO.
     *
     * @param matriculaExistente Objeto `MatriculaModel` representando a matrícula a ser atualizada.
     * @return Objeto `MatriculaDto` representando a matrícula atualizada.
     */
    public MatriculaDto Atualizar(MatriculaModel matriculaExistente) {
        return matriculaRepository.save(matriculaExistente).toDto();
    }

    /**
     * Deleta uma matrícula com base no ID fornecido.
     *
     * Este método tenta deletar a matrícula do repositório com o ID fornecido. Não há tratamento específico para erros;
     * se a matrícula não existir ou não puder ser deletada, o repositório lançará uma exceção apropriada.
     *
     * @param id ID da matrícula a ser deletada.
     */
    public void Deletar(int id) {
        matriculaRepository.deleteById(id);
    }
}
