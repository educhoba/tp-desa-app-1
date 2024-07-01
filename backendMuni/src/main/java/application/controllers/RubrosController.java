
package application.controllers;

import application.models.Desperfectos;
import application.models.Rubros;
import application.services.RubrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rubros")
public class RubrosController {

    @Autowired
    private RubrosService service;

    private RubrosController() { }

    @GetMapping
    public ResponseEntity<List<Rubros>> listar() {
        return ResponseEntity.ok(service.listar());
    }
    @GetMapping("/{idRubro}")
    public ResponseEntity<Rubros> getSitio(@PathVariable Integer idRubro) {
        return ResponseEntity.ok(service.buscarPorId(idRubro));
    }
}

