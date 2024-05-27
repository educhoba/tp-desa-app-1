
package old;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/model")
public class ModelController {


    //@Autowired
    private ModelService service;

    private ModelController() { }

    @GetMapping
    public ResponseEntity<List<Model>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{pk}")
    public ResponseEntity<Model> buscar(@PathVariable String pk) {
        try{
            Model ret = service.buscarPorPK(pk);
            return ResponseEntity.ok(ret);
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Model> buscar(@PathVariable Integer id) {
        try{
            Model ret = service.buscarPorId(id);
            return ResponseEntity.ok(ret);
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping public Model guardar(@RequestBody Model body) {
        try{
            service.guardar(body);
            return body;
        }
        catch (Exception ex){
            return null;
        }
    }
}

