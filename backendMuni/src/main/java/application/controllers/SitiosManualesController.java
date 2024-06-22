
package application.controllers;

import application.exceptions.SitioManualException;
import application.exceptions.UsuarioException;
import application.models.SitiosManuales;
import application.services.SitiosManualesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sitiosManuales")
public class SitiosManualesController {

    @Autowired
    private SitiosManualesService service;

    private SitiosManualesController() { }

    @GetMapping
    public ResponseEntity<List<SitiosManuales>> listar() {
        return ResponseEntity.ok(service.listar());
    }
    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody SitiosManuales sitioManual) {
        try{
            service.registrarSitio(sitioManual);
        }
        catch (SitioManualException ex){
            return ResponseEntity.badRequest()
                    .body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Sitio registrado.");
    }

}

