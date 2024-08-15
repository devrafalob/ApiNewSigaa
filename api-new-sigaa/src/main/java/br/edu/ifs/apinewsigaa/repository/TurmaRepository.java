package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.TurmaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<TurmaModel, Integer> {

    Optional<TurmaModel> findByIdDisciplina(int id);
    void deleteByIdDisciplina(int id);
    Optional<TurmaModel> findByIdProfessor(int id);
    void deleteByIdProfessor(int id);
    Optional<TurmaModel> findByDataInicio(Date date);
    boolean existsByIdDisciplina(int id);
    boolean existsByIdProfessor(int id);
}
