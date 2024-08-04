package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel,Integer>  { //<AlunoModel,Integer> identifica o tipo de dado que será trabalhado

    void deleteByMatricula(String matricula);

    Optional<AlunoModel> findByMatricula(String matricula);

    Boolean existsByMatricula(String matricula);

    Boolean existsByCpf(String cpf);

    List<AlunoModel> findByNomeContaining(String nome);

    List<AlunoModel> findByOrderByNomeDesc();

    Optional<AlunoModel> findByEmail(String email);

    @Query(value = "SELECT * FROM aluno a " + "WHERE a.email = :email", nativeQuery = true)
    List<AlunoModel> ObterAlunoPorEmail(@Param("email") String email);
}
