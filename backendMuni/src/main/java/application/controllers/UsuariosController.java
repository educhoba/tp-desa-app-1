
package application.controllers;

import application.exceptions.UsuarioException;
import application.models.Usuarios;
import application.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    private UsuariosController() { }

    @GetMapping
    public ResponseEntity<List<Usuarios>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{documento}")
    public ResponseEntity<Usuarios> buscar(@PathVariable String documento) {

        Usuarios usuarios = usuarioService.buscarUsuario(documento);

        if(usuarios == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.ok(usuarios);
        }
    }

    @PostMapping("/registrarUsuario")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuarios usuarios){
        try{
            usuarioService.registrarUsuario(usuarios);
        }
        catch (UsuarioException ex){
            return ResponseEntity.badRequest()
                    .body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado.");
    }

    @PostMapping("/cambiarContrasenia")
    public Usuarios cambiarContrasenia(@RequestBody Usuarios usuarios) {
        try{
            usuarioService.cambiarContrasenia(usuarios);
            return usuarios;
        }
        catch (UsuarioException ex){
            return null;
        }
    }

}

