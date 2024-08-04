package br.edu.ifs.apinewsigaa.model;

import br.edu.ifs.apinewsigaa.rest.dto.AlunoDto;
import jakarta.persistence.*; //persistencia de dados
import lombok.Data; //Gera em tempo de DEV getters, setters e construtores
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data // GETTERS/SETTERS/CONSTRUCTORS
@Entity //IDENTIFICA A CLASSE COMO UMA TABELA DE UM BD GERENCIADA PELO SPRING
@Table(name = "aluno") //QUAL O NOME DA TABELA ESPECIFICA NO BANCO?
public class AlunoModel {
    private ModelMapper modelMapper;

    @Id //IDENTIFICA A PK DO BD
    @GeneratedValue(strategy = GenerationType.IDENTITY) //SERVE PARA ADICIONAR OS DADOS NO BANCO
    private int id;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "dataNascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "celular", length = 14, nullable = false, unique = true)
    private String celular;

    @Column(name = "apelido", length = 255, nullable = true)
    private String apelido;

    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    public AlunoDto toDto(){
        return modelMapper.map(this, AlunoDto.class);
    }
}
