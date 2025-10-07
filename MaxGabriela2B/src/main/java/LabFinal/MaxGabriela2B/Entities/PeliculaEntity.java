package LabFinal.MaxGabriela2B.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PELICULAS")
@Getter @Setter @ToString @EqualsAndHashCode
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PELICULAS")
    @SequenceGenerator(name = "SEQ_PELICULAS", sequenceName = "SEQ_PELICULAS", allocationSize = 1)
    @Column(name = "ID_PELICULA")
    private Long idPelicula;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "GENERO")
    private String genero;

    @Column(name = "ANO_ESTRENO")
    private Long anoEstreno;

    @Column(name = "DURACION_MIN")
    private Long duracionMin;

    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;

    //RELACIOn
    @OneToMany(mappedBy = "idPelicula", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PremioEntity> premios = new ArrayList<>();
}
