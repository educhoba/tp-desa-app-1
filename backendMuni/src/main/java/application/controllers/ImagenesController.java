
package application.controllers;

import application.exceptions.ImagenException;
import application.models.Imagenes;
import application.services.ImagenesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Imagenes")
public class ImagenesController {

    @Autowired
    private ImagenesService service;

    private ImagenesController() { }

    @GetMapping
    public ResponseEntity<List<Imagenes>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/idDenuncia={idDenuncia}")
    public ResponseEntity<List<Imagenes>> listarPorIdDenuncia(@PathVariable Integer idDenuncia) {
        return ResponseEntity.ok(service.listarPorIdDenuncia(idDenuncia));
    }
    @GetMapping("/idReclamo={idReclamo}")
    public ResponseEntity<List<Imagenes>> listarPorIdReclamo(@PathVariable Integer idReclamo) {
        return ResponseEntity.ok(service.listarPorIdReclamo(idReclamo));
    }
    @GetMapping("/idServicio={idServicio}")
    public ResponseEntity<List<Imagenes>> listarPorIdServicio(@PathVariable Integer idServicio) {
        return ResponseEntity.ok(service.listarPorIdServicio(idServicio));
    }
    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody Imagenes imagen) {
        try{
            service.guardarImagen(imagen);
        }
        catch (ImagenException ex){
            return ResponseEntity.badRequest()
                    .body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Imagen guardada exitosamente.");
    }
}

