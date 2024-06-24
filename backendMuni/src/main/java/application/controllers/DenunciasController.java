
package application.controllers;

import application.exceptions.DenunciaException;
import application.exceptions.ImagenException;
import application.models.Denuncias;
import application.models.Imagenes;
import application.services.DenunciasService;
import application.services.ImagenesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/denuncias")
public class DenunciasController {

    @Autowired
    private DenunciasService service;

    @Autowired
    private ImagenesService imagenesService; //deberia estar en el controlador

    private DenunciasController() { }

    @GetMapping
    public ResponseEntity<List<Denuncias>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{idDenuncia}")
    public ResponseEntity<Denuncias> buscar(@PathVariable Integer idDenuncia) {
        Denuncias obj = service.buscarPorId(idDenuncia);
        if(obj != null){
            return ResponseEntity.ok(obj);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody Denuncias denuncias){
        try{
            service.registrar(denuncias);

            //getimagenes, contar cantidad, error
            Integer id = denuncias.getIdDenuncias();
            for (Imagenes img : denuncias.getImagenes()) {
                img.setIdDenuncia(id);
                imagenesService.guardarImagen(img);
            }
        }
        catch (DenunciaException | ImagenException ex){
            return ResponseEntity.badRequest()
                    .body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Denuncia generada.");

    }@PostMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Denuncias denuncias){
        try{
            service.actualizar(denuncias);
        }
        catch (DenunciaException ex){
            return ResponseEntity.badRequest()
                    .body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Denuncia actualizada.");
    }
    @GetMapping("/listarPorDocumento/{documento}")
    public ResponseEntity<List<Denuncias>> listarPorDocumento(@PathVariable String documento) {
        List<Denuncias> obj = service.listarPorDocumento(documento);
        if(obj != null){
            return ResponseEntity.ok(obj);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

