package br.edu.ifs.apinewsigaa.model.projection;

import java.util.Date;

public interface DisciplinaComAlunoProjection {
    int getId();
    String getNome();
    String getCpf();
    String getEmail();
    Date getDataNascimento();
    String getcelular();
    String getApelido();
    String getMatricula();
}
