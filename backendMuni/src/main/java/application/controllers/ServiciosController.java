
package application.controllers;

import application.exceptions.ReclamoException;
import application.models.Reclamos;
import application.models.Servicios;
import application.models.Vecinos;
import application.services.ServiciosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ServiciosController {

    @Autowired
    private ServiciosService service;

    private ServiciosController() { }

    @GetMapping
    public ResponseEntity<List<Servicios>> listar() {
        return ResponseEntity.ok(service.listar());
    }
    @GetMapping("/{idServicios}")
    public ResponseEntity<Servicios> buscar(@PathVariable Integer idServicios) {
        return ResponseEntity.ok(service.buscarPorId(idServicios));
    }
    @GetMapping("/tipo={tipo}")
    public ResponseEntity<List<Servicios>> listarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(service.listarTipo(tipo));
    }
    @GetMapping("/tipo={tipo}/rubro={rubro}")
    public ResponseEntity<List<Servicios>> listarPorTipoYRubro(@PathVariable String tipo,@PathVariable String rubro) {
        return ResponseEntity.ok(service.listarTipoYRubro(tipo,rubro));
    }
    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody Servicios servicio){
        try{
            service.registrar(servicio);
        }
        catch (ReclamoException ex){
            return ResponseEntity.badRequest()
                    .body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Servicio registrado.");
    }

}

