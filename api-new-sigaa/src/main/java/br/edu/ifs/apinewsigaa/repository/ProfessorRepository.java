package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.ProfessorModel;
import br.edu.ifs.apinewsigaa.model.projection.DisciplinaComAlunoProjection;
import br.edu.ifs.apinewsigaa.model.projection.DisciplinaProfessorProfection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //CRUD
public interface ProfessorRepository extends JpaRepository<ProfessorModel, Integer> { // ENTREGA AS OPERACOES BASICAS BASEADAS NA CHAVE PRIMARIA
    Optional<ProfessorModel> findByMatricula(String matricula);
    void deleteByMatricula(String matricula);
    Optional<ProfessorModel> findByCpf(String cpf);
    void deleteByCpf(String cpf);
    Optional<ProfessorModel> findByEmail(String email);
    Optional<ProfessorModel> findByCelular(String celular);
    void deleteByCelular(String celular);
    boolean existsByMatricula(String matricula);
    boolean existsByCpf(String cpf);

    @Query(value = """
            SELECT d.id
            ,      d.nome
            ,      d.numeroCreditos as numeroCreditos
                FROM turma as t
                INNER JOIN professor p ON p.id = t.idProfessor
                INNER JOIN disciplina d ON d.ID = t.idDisciplina
                WHERE p.matricula = :matricula
            """, nativeQuery = true)
    List<DisciplinaProfessorProfection> obterDisciplinasProfessor(@Param("matricula") String matricula);

    @Query(value = """
            SELECT DISTINCT a.id
            ,      a.nome
            ,      a.cpf
            ,      a.email as email
            ,      a.dataNascimento
            ,      a.celular
            ,      a.apelido
            ,      a.matricula
                FROM matricula as m
                INNER JOIN aluno a ON a.id = m.idAluno
                INNER JOIN turma t ON t.id = m.idTurma
                INNER JOIN disciplina d ON d.ID = t.idDisciplina
                INNER JOIN professor p ON p.id = t.idProfessor
                WHERE d.id = :idDisciplina
            """, nativeQuery = true)
    List<DisciplinaComAlunoProjection> obterDisciplinaAlunos(@Param("idDisciplina") int disciplina);

}
