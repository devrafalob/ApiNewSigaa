package br.edu.ifs.apinewsigaa.model;

import br.edu.ifs.apinewsigaa.rest.dto.TurmaDto;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@Entity
@Table(name = "turma")
public class TurmaModel {
    private ModelMapper modelMapper;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "dataInicio", nullable = false)
    private Date dataInicio;
    @Column(name = "dataFim", nullable = false)
    private Date dataFim;
    @Column(name = "idProfessor", nullable = false)
    private int idProfessor;
    @Column(name = "idDisciplina", nullable = false)
    private int idDisciplina;

    public TurmaDto toDto(){
        return modelMapper.map(this, TurmaDto.class);
    }
}
