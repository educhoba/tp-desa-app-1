
package application.controllers;

import application.exceptions.PersonalException;
import application.exceptions.VecinoException;
import application.models.Personal;
import application.models.Vecinos;
import application.services.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    private PersonalController() { }

    @GetMapping
    public ResponseEntity<List<Personal>> listar() {
        return ResponseEntity.ok(personalService.listar());
    }

    @GetMapping("/{documento}")
    public ResponseEntity<Personal> buscar(@PathVariable String documento) {
        try{
            Personal personal = personalService.buscarPersonal(documento);
            if(personal != null){
                return ResponseEntity.ok(personal);
            }
            else{
                return ResponseEntity.ok(null);
            }
        }
        catch (PersonalException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

