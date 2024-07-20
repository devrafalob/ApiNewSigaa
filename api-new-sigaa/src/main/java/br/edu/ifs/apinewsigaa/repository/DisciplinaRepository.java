package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.DisciplinaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<DisciplinaModel, Integer> {
}
