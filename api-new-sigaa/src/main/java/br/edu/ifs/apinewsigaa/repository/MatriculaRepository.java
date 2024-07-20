package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.MatriculaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<MatriculaModel,Integer> {
}
