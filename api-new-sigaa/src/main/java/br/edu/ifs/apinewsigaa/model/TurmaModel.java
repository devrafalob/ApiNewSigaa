package br.edu.ifs.apinewsigaa.model;

import br.edu.ifs.apinewsigaa.rest.dto.TurmaDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@Entity
@Table(name = "turma")
public class TurmaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Erro: A data de inicio não pode ser nulo.")
    @Column(name = "dataInicio", nullable = false)
    private Date dataInicio;

    @NotNull(message = "Erro: A data de fim não pode ser nulo.")
    @Column(name = "dataFim", nullable = false)
    private Date dataFim;

    @Column(name = "idProfessor", nullable = false)
    private int idProfessor;

    @Column(name = "idDisciplina", nullable = false)
    private int idDisciplina;

    public TurmaDto toDto(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, TurmaDto.class);
    }
}
