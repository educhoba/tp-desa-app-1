
package application.controllers;

import application.models.Sitios;
import application.services.SitiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sitios")
public class SitiosController {

    @Autowired
    private SitiosService service;

    private SitiosController() { }

    @GetMapping
    public ResponseEntity<List<Sitios>> listar() {
        return ResponseEntity.ok(service.listar());
    }

}

