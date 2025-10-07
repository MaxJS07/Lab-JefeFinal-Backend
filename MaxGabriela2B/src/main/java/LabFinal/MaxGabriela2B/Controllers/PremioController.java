package LabFinal.MaxGabriela2B.Controllers;

import LabFinal.MaxGabriela2B.Models.DTO.PremioDTO;
import LabFinal.MaxGabriela2B.Services.PremioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/premios")
public class PremioController {

    //Service
    @Autowired
    private PremioService service;

    @GetMapping("/getPremios")
    public List<PremioDTO> getPremios(){
        return service.getPremios();
    }
}
