package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.MatriculaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<MatriculaModel,Integer> {
    Optional<MatriculaModel> findByIdTurma(Integer integer);
    Optional<MatriculaModel> findByIdAluno(Integer integer);
    boolean existsByIdTurma(int id);
    boolean existsByIdAluno(int id);
    void deleteByIdTurma(int id);
}
