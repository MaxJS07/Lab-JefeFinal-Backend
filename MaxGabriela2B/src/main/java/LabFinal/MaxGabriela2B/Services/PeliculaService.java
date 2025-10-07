package LabFinal.MaxGabriela2B.Services;

import LabFinal.MaxGabriela2B.Entities.PeliculaEntity;
import LabFinal.MaxGabriela2B.Models.DTO.PeliculaDTO;
import LabFinal.MaxGabriela2B.Repositories.PeliculaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @Slf4j
public class PeliculaService {

    //REPO
    @Autowired
    private PeliculaRepository repo;


    //METODO GET
    public List<PeliculaDTO> getAllMovies(){
        List<PeliculaEntity> peliculas = repo.findAll();
        return peliculas.stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }


    //METODOS CONVERSION
    private PeliculaDTO convertirADto(PeliculaEntity entity){
        PeliculaDTO dto = new PeliculaDTO();

        dto.setIdPelicula(entity.getIdPelicula());
        dto.setTitulo(entity.getTitulo());
        dto.setDirector(entity.getDirector());
        dto.setGenero(entity.getGenero());
        dto.setAnoEstreno(entity.getAnoEstreno());
        dto.setDuracionMin(entity.getDuracionMin());
        dto.setFechaCreacion(entity.getFechaCreacion());

        return dto;
    }

    private PeliculaEntity convertirAentity(PeliculaDTO dto){
        PeliculaEntity entity = new PeliculaEntity();

        entity.setTitulo(dto.getTitulo());
        entity.setDirector(dto.getDirector());
        entity.setGenero(dto.getGenero());
        entity.setAnoEstreno(dto.getAnoEstreno());
        entity.setDuracionMin(dto.getDuracionMin());
        entity.setFechaCreacion(dto.getFechaCreacion());

        return entity;
    }
}
