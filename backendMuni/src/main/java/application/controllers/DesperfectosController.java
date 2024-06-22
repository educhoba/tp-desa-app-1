
package application.controllers;

import application.models.Desperfectos;
import application.services.DesperfectosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desperfectos")
public class DesperfectosController {

    @Autowired
    private DesperfectosService service;

    private DesperfectosController() { }

    @GetMapping
    public ResponseEntity<List<Desperfectos>> listar() {
        return ResponseEntity.ok(service.listar());
    }

}

