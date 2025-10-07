package LabFinal.MaxGabriela2B.Controllers;

import LabFinal.MaxGabriela2B.Models.DTO.PeliculaDTO;
import LabFinal.MaxGabriela2B.Services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin
public class PeliculaController {

    //Service
    @Autowired
    private PeliculaService service;

    //METODO GET
    @GetMapping("/getPeliculas")
    public List<PeliculaDTO> getAllPelis(){
        return service.getAllMovies();
    }
}
