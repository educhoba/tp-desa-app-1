
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
        try{
            Usuarios usuarios = usuarioService.buscarUsuario(documento);
            return ResponseEntity.ok(usuarios);
        }
        catch (UsuarioException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
    //si esta autorizado devuelve la persona, sino devuelve 1
    @GetMapping("/login")
    public ResponseEntity<Usuarios> login(@RequestBody Map<String, String> requestBody) {
        String documento = requestBody.get("documento");
        String contrasenia = requestBody.get("contrasenia");

        System.out.println("login "+documento+" "+contrasenia);
        try {
            Usuarios usuarios1 = usuarioService.buscarUsuario(documento);
            if( usuarios1.getContrasenia().equals(contrasenia))
                return ResponseEntity.ok(usuarios1);
        }
        catch (UsuarioException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

