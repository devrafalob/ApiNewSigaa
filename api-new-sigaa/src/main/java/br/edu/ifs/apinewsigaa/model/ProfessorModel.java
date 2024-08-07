package br.edu.ifs.apinewsigaa.model;

import br.com.caelum.stella.validation.CPFValidator;
import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.rest.dto.ProfessorDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@Entity
@Table(name = "professor")
public class ProfessorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = "[\\p{L} .'-]+",
            message = "ERRO: O nome deve conter apenas letras e alguns caracteres especiais permitidos(espaços, pontos, acentos, apóstrofos e hífens)")
    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Email(message = "Erro: O endereço de email está inválido."
            , regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
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

    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    @Column(name = "endereço", nullable = false, unique = false)
    private String endereco;

    public ProfessorDto toDto(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ProfessorDto.class);
    }
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
