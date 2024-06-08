
package application.controllers;

import application.exceptions.VecinoException;
import application.models.Vecinos;
import application.services.VecinosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vecinos")
public class VecinosController {

    @Autowired
    private VecinosService vecinosService;

    private VecinosController() { }

    @GetMapping
    public ResponseEntity<List<Vecinos>> listar() {
        return ResponseEntity.ok(vecinosService.listar());
    }

    @GetMapping("/{documento}")
    public ResponseEntity<Vecinos> buscar(@PathVariable String documento) {
        try{
            Vecinos vecino = vecinosService.buscarVecino(documento);
            if(vecino != null){
                return ResponseEntity.ok(vecino);
            }
            else{
                return ResponseEntity.ok(null);
            }
        }
        catch (VecinoException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

