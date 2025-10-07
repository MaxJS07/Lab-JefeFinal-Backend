package LabFinal.MaxGabriela2B.Models.DTO;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString @EqualsAndHashCode
public class PeliculaDTO {

    private Long idPelicula;

    private String titulo;

    private String director;

    private String genero;

    private Long anoEstreno;

    private Long duracionMin;

    private Date fechaCreacion;
}
