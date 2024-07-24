package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel,Integer> {

    Optional<AlunoModel> findByMatricula(Integer integer);
    void deleteByMatricula(int matricula);
    Optional<AlunoModel> findByCpf(String cpf);
    void deleteByCpf(String cpf);
    Optional<AlunoModel> findByEmail(String email);
    void deleteByEmail(String email);
    Optional<AlunoModel> findByCelular(String celular);
    void deleteByCelular(String celular);
    boolean existsByMatricula(int matricula);
}
