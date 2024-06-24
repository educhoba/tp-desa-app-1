
package application.controllers;

import application.exceptions.ImagenException;
import application.exceptions.ReclamoException;
import application.exceptions.UsuarioException;
import application.models.Imagenes;
import application.models.Reclamos;
import application.models.Usuarios;
import application.services.ImagenesService;
import application.services.ReclamosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reclamos")
public class ReclamosController {

    @Autowired
    private ReclamosService service;

    @Autowired
    private ImagenesService imagenesService; //deberia estar en el controlador

    private ReclamosController() { }

    @GetMapping
    public ResponseEntity<List<Reclamos>> listar() {
        return ResponseEntity.ok(service.listar());
    }
    @GetMapping("/documento={documento}")
    public ResponseEntity<List<Reclamos>> listarPorDocumento(@PathVariable String documento) {
        return ResponseEntity.ok(service.listarPorDocumento(documento));
    }
    @GetMapping("/legajo={legajo}")
    public ResponseEntity<List<Reclamos>> listarPorLegajo(@PathVariable Integer legajo) {
        return ResponseEntity.ok(service.listarPorLegajo(legajo));
    }
    //@GetMapping("/listarPorRubro?={rubro}")
    //public ResponseEntity<List<Reclamos>> listarPorRubro(@PathVariable String rubro) {
    //    return ResponseEntity.ok(service.listarPorRubro(rubro));
    //}
    @GetMapping("/{idReclamo}")
    public ResponseEntity<Reclamos> buscar(@PathVariable Integer idReclamo) {
        Reclamos obj = service.buscarPorId(idReclamo);
        if(obj != null){
            return ResponseEntity.ok(obj);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody Reclamos reclamos){
        try{
            service.registrar(reclamos);
            //getimagenes, contar cantidad, error
            Integer id = reclamos.getIdReclamo();
            for (Imagenes img : reclamos.getImagenes()) {
                img.setIdReclamo(id);
                imagenesService.guardarImagen(img);
            }
        }
        catch (ReclamoException | ImagenException ex){
            return ResponseEntity.badRequest()
                    .body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Reclamo registrado.");
    }
    @PostMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Reclamos reclamos){
        try{
            service.actualizar(reclamos);
        }
        catch (ReclamoException ex){
            return ResponseEntity.badRequest()
                    .body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Reclamo actualizado.");
    }
}

