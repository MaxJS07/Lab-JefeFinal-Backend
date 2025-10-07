package LabFinal.MaxGabriela2B.Controllers;

import LabFinal.MaxGabriela2B.Exceptions.PremioNoEncontradoException;
import LabFinal.MaxGabriela2B.Models.DTO.PremioDTO;
import LabFinal.MaxGabriela2B.Services.PremioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/premios")
@CrossOrigin
public class PremioController {

    //Service
    @Autowired
    private PremioService service;

    @GetMapping("/getPremios")
    public List<PremioDTO> getPremios(){
        return service.getPremios();
    }

    //POST
    @PostMapping("/postPremio")
    public ResponseEntity<Map<String, Object>> registerPremio(
        @Valid @RequestBody PremioDTO premio,
        BindingResult bindingResult,
        HttpServletRequest request
    ){
        if(bindingResult.hasErrors()){
            Map<String, String> validationErrors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    validationErrors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(Map.of(
               "status", "ERROR_DE_VALIDACION",
               "errors", validationErrors
            ));
        }

        try{
            PremioDTO reply = service.postPremio(premio);

            if(reply == null){
                return ResponseEntity.badRequest().body(Map.of(
                   "status", "Inserción incorrecta",
                   "errorType", "ERROR_DE_VALIDACION",
                   "message", "Datos incorrectos"
                ));
            }
            else{
                return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                        "satus", "Éxito",
                        "data", reply
                ));
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "error",
                    "message", "Error al registrar",
                    "detail", e.getMessage()
            ));
        }
    }

    //PUT
    @PutMapping("/putPremio/{id}")
    public ResponseEntity<?> updatePremio(
            @PathVariable Long id,
            @Valid @RequestBody PremioDTO premio,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            Map<String, String> validationErrors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    validationErrors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "ERROR_DE_VALIDACION",
                    "errors", validationErrors
            ));
        }

        try{
            PremioDTO premioUpdated = service.putPremio(id, premio);
            return ResponseEntity.ok().body(Map.of(
                    "satus", "Actualizado con éxito",
                    "data", premioUpdated
            ));
        }
        catch (PremioNoEncontradoException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletePremio/{id}")
    public ResponseEntity<Map<String, Object>> deletePremio(@PathVariable Long id){
        try{
            if(!service.eliminarPremio(id)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("Error", "Premio no encontrado")
                        .body(Map.of(
                           "error", "No encontrado",
                           "message", "No se encontró el premio a eliminar",
                           "timestamp", Instant.now().toString()
                        ));
            }
            else{
                return ResponseEntity.ok().body(Map.of(
                        "status", "Eliminado",
                        "message", "Se eliminó el premio correctamente"
                ));
            }
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",
                    "message", "Error al eliminar el premio",
                    "detail", e.getMessage()
            ));
        }
    }
}
