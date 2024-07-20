package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.ProfessorModel;
import br.edu.ifs.apinewsigaa.model.projection.DisciplinaProfessorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //CRUD
public interface ProfessorRepository extends JpaRepository<ProfessorModel, Integer> { // ENTREGA AS OPERACOES BASICAS BASEADAS NA CHAVE PRIMARIA
    Optional<ProfessorModel> findByMatricula(int matricula);
    void deleteByMatricula(int matricula);
    Optional<ProfessorModel> findByCpf(String cpf);
    void deleteByCpf(String cpf);
    Optional<ProfessorModel> findByEmail(String email);
    void deleteByEmail(String email);
    Optional<ProfessorModel> findByCelular(String celular);
    void deleteByCelular(String celular);
    boolean existsByMatricula(int matricula);
    @Query(value = """
            SELECT	p.matricula as matricula
            	,	p.nome as nomeProfessor
            	,	d.nome as nomeDisciplina
            	,	d.numeroCreditos as numeroCreditos 
            	FROM professor p
            		INNER JOIN turma t
            			ON (p.id = t.idProfessor)
            		INNER JOIN disciplina d
            			ON (d.id = t.idDisciplina)
            	WHERE p.matricula = :matricula
            """, nativeQuery = true)
    List<DisciplinaProfessorProjection> ObterDisciplinasProfessor(@Param("matricula") int matricula);

}
