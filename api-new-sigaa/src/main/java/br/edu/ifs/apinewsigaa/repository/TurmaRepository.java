package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.TurmaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<TurmaModel, Integer> {
    //Devo buscar a turma por meio de Query's para Professor e Disciplina ?
}
