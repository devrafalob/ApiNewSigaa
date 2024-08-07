package br.edu.ifs.apinewsigaa.rest.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class AlunoDto { //DTO é a fronteira da API ligando o front e o back
                        //A requisição http GET será influenciada pelos campos instanciados no DTO
    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "celular", length = 14, nullable = false, unique = true)
    private String celular;

    @Column(name = "apelido", length = 255, nullable = true)
    private String apelido;

    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;
}
