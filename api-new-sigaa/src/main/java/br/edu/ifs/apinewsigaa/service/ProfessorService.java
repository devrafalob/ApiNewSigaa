package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public void teste(){

        //professorRepository.
    }
}
