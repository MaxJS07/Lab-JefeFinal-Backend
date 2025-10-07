package LabFinal.MaxGabriela2B.Services;

import LabFinal.MaxGabriela2B.Entities.PeliculaEntity;
import LabFinal.MaxGabriela2B.Entities.PremioEntity;
import LabFinal.MaxGabriela2B.Exceptions.PremioNoEncontradoException;
import LabFinal.MaxGabriela2B.Exceptions.PremioNoRegistradoException;
import LabFinal.MaxGabriela2B.Models.DTO.PremioDTO;
import LabFinal.MaxGabriela2B.Repositories.PeliculaRepository;
import LabFinal.MaxGabriela2B.Repositories.PremioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @Slf4j
public class PremioService {

    //REPOS
    @Autowired
    private PremioRepository repo;

    @Autowired
    private PeliculaRepository repoPeli;

    //GET
    public List<PremioDTO> getPremios(){
        List<PremioEntity> premios = repo.findAll();
        return premios.stream()
                .map(this::convertiraDto)
                .collect(Collectors.toList());
    }

    //POST
    public PremioDTO postPremio(PremioDTO dto){
        if(dto.getIdPelicula() == null || dto.getNombrePremio().isBlank() || dto.getCategoria().isBlank()
            || dto.getAnoPremio() == null || dto.getResultado().isBlank() || dto.getFechaRegisto() == null
        ){
            throw new IllegalArgumentException("Un campo viene vacío");
        }
        else{
            try{
                PremioEntity entity = convertirAentity(dto);
                PremioEntity premioSaved = repo.save(entity);

                return convertiraDto(premioSaved);
            }
            catch(Exception e){
                log.error("Hubo un error al insertar el premio: " + e.getMessage());
                throw new PremioNoRegistradoException("No se pudo registrar el nuevo premio: " + e.getMessage());
            }
        }
    }

    //PUT
    public PremioDTO putPremio(Long idPremio, PremioDTO dto){

        PremioEntity premioExis = repo.findById(idPremio).orElseThrow(() -> new PremioNoEncontradoException("No se encontró el premio que se queria actualizar"));

        //Actualizamos
        if(dto.getIdPelicula() != null){
            PeliculaEntity peli = repoPeli.findById(dto.getIdPelicula())
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró el Id de la pelicula que se quiere actualizar."));
            premioExis.setIdPelicula(peli);
        }

        premioExis.setNombrePremio(dto.getNombrePremio());
        premioExis.setCategoria(dto.getCategoria());
        premioExis.setAnoPremio(dto.getAnoPremio());
        premioExis.setResultado(dto.getResultado());
        premioExis.setFechaRegisto(dto.getFechaRegisto());

        PremioEntity premioUpdated = repo.save(premioExis);

        return convertiraDto(premioUpdated);
    }



    //Metodos de conversion
    private PremioDTO convertiraDto (PremioEntity entity){
        PremioDTO dto = new PremioDTO();

        dto.setIdPremio(entity.getIdPremio());

        //Llave foránea
        if(entity.getIdPelicula() != null){
            dto.setIdPelicula(entity.getIdPelicula().getIdPelicula());
            dto.setTituloPelicula(entity.getIdPelicula().getTitulo());
        }
        else{
            dto.setIdPelicula(null);
            dto.setTituloPelicula("Sin pelicula asignada");
        }

        dto.setNombrePremio(entity.getNombrePremio());
        dto.setCategoria(entity.getCategoria());
        dto.setAnoPremio(entity.getAnoPremio());
        dto.setResultado(entity.getResultado());
        dto.setFechaRegisto(entity.getFechaRegisto());

        return dto;
    }

    private PremioEntity convertirAentity (PremioDTO dto){
        PremioEntity entity = new PremioEntity();

        if(dto.getIdPelicula() != null){
            PeliculaEntity pelicula = repoPeli.findById(dto.getIdPelicula())
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró la pelicula con el ID: " + dto.getIdPelicula()));
            entity.setIdPelicula(pelicula);
        }

        entity.setNombrePremio(dto.getNombrePremio());
        entity.setCategoria(dto.getCategoria());
        entity.setAnoPremio(dto.getAnoPremio());
        entity.setResultado(dto.getResultado());
        entity.setFechaRegisto(dto.getFechaRegisto());

        return entity;
    }

}
