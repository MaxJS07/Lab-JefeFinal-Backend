package LabFinal.MaxGabriela2B.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "PREMIOS")
@Getter @Setter @ToString @EqualsAndHashCode
public class PremioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PREMIOS")
    @SequenceGenerator(name = "SEQ_PREMIOS", sequenceName = "SEQ_PREMIOS", allocationSize = 1)
    @Column(name = "ID_PREMIO")
    private Long idPremio;

    @ManyToOne
    @JoinColumn(name = "ID_PELICULA", referencedColumnName = "ID_PELICULA")
    private PeliculaEntity idPelicula;

    @Column(name = "NOMBRE_PREMIO")
    private String nombrePremio;

    @Column(name = "CATEGORIA")
    private String categoria;

    @Column(name = "ANO_PREMIO")
    private Long anoPremio;

    @Column(name = "RESULTADO")
    private String resultado;

    @Column(name = "FECHA_REGISTRO")
    private Date fechaRegisto;

}
