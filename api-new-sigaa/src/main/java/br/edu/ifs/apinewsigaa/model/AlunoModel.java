package br.edu.ifs.apinewsigaa.model;

import br.com.caelum.stella.validation.CPFValidator;
import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.rest.dto.AlunoDto;
import jakarta.persistence.*; //persistencia de dados
import lombok.Data; //Gera em tempo de DEV getters, setters e construtores
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@Entity
@Table(name = "aluno")
public class AlunoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, AlunoDto.class);
    }

    public boolean validarCPF(String cpf){
        CPFValidator cpfValidator = new CPFValidator();
        try{
            cpfValidator.assertValid(cpf);
            return true;
        }catch(Exception e){
            throw new DataIntegrityException("CPF inválido!");
        }
    }
}
