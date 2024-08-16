package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.exception.InternalServerErrorException;
import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.DisciplinaModel;
import br.edu.ifs.apinewsigaa.repository.DisciplinaRepository;
import br.edu.ifs.apinewsigaa.rest.dto.DisciplinaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository disciplinaRepository;


    /**
     * Obtém todas as disciplinas e as converte para DTOs.
     *
     * Este método consulta o repositório de disciplinas para obter uma lista de todas as disciplinas armazenadas no banco de dados.
     * Em seguida, converte cada instância de `DisciplinaModel` para um `DisciplinaDto` usando o método `toDto()` e coleta todos os DTOs em uma lista.
     *
     * @return Lista de objetos `DisciplinaDto` representando todas as disciplinas.
     */
    public List<DisciplinaDto> ObterTodos() {
        List<DisciplinaModel> disciplinaList = disciplinaRepository.findAll();
        return disciplinaList.stream().map(disciplina -> disciplina.toDto()).collect(Collectors.toList());
    }

    /**
     * Obtém uma disciplina com base no nome fornecido.
     *
     * Este método procura uma disciplina associada ao nome fornecido. Se a disciplina não for encontrada, lança uma exceção personalizada
     * com uma mensagem de erro indicando que o nome não foi encontrado.
     *
     * @param nome Nome da disciplina que se deseja obter.
     * @return Objeto `DisciplinaDto` representando a disciplina associada ao nome.
     * @throws ObjectNotFoundException Se a disciplina associada ao nome não for encontrada.
     */
    public DisciplinaDto ObterPorNome(String nome) {
        Optional<DisciplinaModel> disciplinaOptional = disciplinaRepository.findByNome(nome);
        DisciplinaModel disciplinaModel = disciplinaOptional.orElseThrow(() ->
                new ObjectNotFoundException("Error: Nome não encontrado. Nome: " + nome));
        return disciplinaModel.toDto();
    }

    /**
     * Salva uma nova disciplina no banco de dados.
     *
     * Este método verifica se já existe uma disciplina com o mesmo nome antes de salvar a nova disciplina. Se não existir, a disciplina é
     * salva no repositório. Caso contrário, lança uma exceção indicando que a disciplina já existe. Além disso, trata exceções de integridade
     * de dados durante o processo de salvamento.
     *
     * @param novaDisciplina Objeto `DisciplinaModel` representando a nova disciplina a ser salva.
     * @return Objeto `DisciplinaDto` representando a disciplina recém-criada.
     * @throws InternalServerErrorException Se já existir uma disciplina com o nome fornecido.
     * @throws DataIntegrityException Se ocorrer um erro de integridade de dados ao salvar a disciplina.
     */
    @Transactional
    public DisciplinaDto Salvar(DisciplinaModel novaDisciplina) {
        if(!disciplinaRepository.existsByNome(novaDisciplina.getNome())) {
            try {
                return disciplinaRepository.save(novaDisciplina).toDto();
            } catch (DataIntegrityException e) {
                throw new DataIntegrityException("Erro ao salvar a disciplina.");
            }
        } else {
            throw new InternalServerErrorException("Erro: Essa disciplina já existe.");
        }
    }

    /**
     * Atualiza uma disciplina existente.
     *
     * Este método verifica se a disciplina a ser atualizada existe no repositório. Se existir, verifica se não há outra disciplina com o mesmo
     * nome e atualiza a disciplina no banco de dados. Caso contrário, lança exceções indicando que a disciplina com o ID ou nome fornecido
     * não foi encontrada. Também trata exceções de integridade de dados durante o processo de atualização.
     *
     * @param disciplinaExistente Objeto `DisciplinaModel` representando a disciplina a ser atualizada.
     * @return Objeto `DisciplinaDto` representando a disciplina atualizada.
     * @throws InternalServerErrorException Se não existir uma disciplina com o nome ou ID fornecido.
     * @throws DataIntegrityException Se ocorrer um erro de integridade de dados ao atualizar a disciplina.
     */
    @Transactional
    public DisciplinaDto Atualizar(DisciplinaModel disciplinaExistente) {
        if(disciplinaRepository.existsById(disciplinaExistente.getId())) {
            if(disciplinaRepository.existsByNome(disciplinaExistente.getNome())) {
                try {
                    return disciplinaRepository.save(disciplinaExistente).toDto();
                } catch (DataIntegrityException e) {
                    throw new DataIntegrityException("Erro ao atualizar a disciplina.");
                }
            } else {
                throw new InternalServerErrorException("Erro: Não existe uma disciplina com esse nome.");
            }
        } else {
            throw new InternalServerErrorException("Erro: Não existe uma disciplina com esse ID.");
        }
    }

    /**
     * Deleta uma disciplina com base no ID fornecido.
     *
     * Este método tenta deletar a disciplina do repositório com o ID fornecido. Se ocorrer um erro de integridade de dados durante a exclusão,
     * uma exceção personalizada é lançada para indicar o problema. Caso a disciplina não seja encontrada, também lança uma exceção personalizada.
     *
     * @param id ID da disciplina a ser deletada.
     * @throws InternalServerErrorException Se a disciplina não for encontrada.
     * @throws DataIntegrityException Se ocorrer um erro de integridade de dados ao deletar a disciplina.
     */
    @Transactional
    public void Deletar(int id) {
        if(disciplinaRepository.existsById(id)) {
            try {
                disciplinaRepository.deleteById(id);
            } catch (DataIntegrityException e) {
                throw new DataIntegrityException("Erro ao deletar a disciplina.");
            }
        } else {
            throw new InternalServerErrorException("Erro: Disciplina não encontrada.");
        }
    }

    public boolean existeDisciplina(int id){
        if(disciplinaRepository.existsById(id)){
            return true;
        }else{
            return false;
        }
    }
}
