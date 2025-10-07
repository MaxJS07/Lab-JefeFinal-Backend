package LabFinal.MaxGabriela2B.Models.DTO;

import LabFinal.MaxGabriela2B.Entities.PeliculaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString @EqualsAndHashCode
public class PremioDTO {

    private Long idPremio;

    private Long idPelicula;
    private String tituloPelicula;

    private String nombrePremio;

    private String categoria;

    private Long anoPremio;

    private String resultado;

    private Date fechaRegisto;
}
