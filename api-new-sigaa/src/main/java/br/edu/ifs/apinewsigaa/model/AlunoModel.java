package br.edu.ifs.apinewsigaa.model;

import br.com.caelum.stella.validation.CPFValidator;
import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.rest.dto.AlunoDto;
import jakarta.persistence.*; //persistencia de dados
import jakarta.validation.constraints.*;
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

    @Email(message = "Erro: Email inválido. O email não pode conter espaços, acentos ou caracteres especiais.", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Erro: O email não pode estar vazio.")
    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @Past(message = "Erro: A data inválida. A data deve estar no passado.")
    @NotNull(message = "Erro: A data de nascimento não pode ser nula.")
    @Column(name = "dataNascimento", nullable = false)
    private Date dataNascimento;

    @Pattern(regexp = "^\\d{2} \\d{5}-\\d{4}$", message = "Erro: Número de celular inválido. Formato esperado: XX XXXXX-XXXX.")
    @Column(name = "celular", length = 14, nullable = false, unique = true)
    private String celular;

    @Column(name = "apelido", length = 255, nullable = true)
    private String apelido;

    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    /**
     * Converte o objeto atual para um DTO (Data Transfer Object) de aluno.
     * Este método utiliza o ModelMapper para mapear os atributos do objeto Aluno para um objeto AlunoDto.
     *
     * @return um AlunoDto contendo os dados do aluno mapeados a partir do objeto atual.
     */
    public AlunoDto toDto(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, AlunoDto.class);
    }

    /**
     * Valida um número de CPF (Cadastro de Pessoa Física) fornecido.
     * Este método utiliza o CPFValidator da biblioteca Caelum Stella para verificar a validade do CPF fornecido.
     *
     * @param cpf o número de CPF a ser validado.
     * @return o número de CPF fornecido se for válido.
     * @throws DataIntegrityException se o CPF fornecido não for válido.
     */
    public String validarCPF(String cpf){
        String cpfLimpo = cpf.replaceAll("\\D", "");
        CPFValidator cpfValidator = new CPFValidator();
        try{
            cpfValidator.assertValid(cpfLimpo);
            cpfValidator.isEligible(cpfLimpo);
            return cpfLimpo;
        }catch(Exception e){
            throw new DataIntegrityException("Erro: CPF inválido!");
        }
    }


}
