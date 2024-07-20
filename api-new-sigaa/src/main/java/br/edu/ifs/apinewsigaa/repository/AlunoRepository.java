package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel,Integer> {
}
