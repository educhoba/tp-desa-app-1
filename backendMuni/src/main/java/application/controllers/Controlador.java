package application.controllers;

import application.exceptions.UsuarioException;
import application.exceptions.VecinoException;
import application.models.*;
import application.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/negocio")
public class Controlador {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private VecinosService vecinosService;

	private static Controlador instancia;

	private Controlador() { }

	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	//@GetMapping("/test")
	//public ResponseEntity<String> test() {
	//	return ResponseEntity.status(HttpStatus.CREATED)
	//			.body("Usuario registrado.");
    //}

	@PostMapping("/registrarVecino")
	public ResponseEntity<String> registrarVecino(@RequestBody Usuarios usuarios){

		Usuarios u = usuarioService.buscarUsuario(usuarios.getDocumento());
		if(u != null){
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Usuario ya existe.");
		}
		try{
			Vecinos v = vecinosService.buscarVecino(usuarios.getDocumento());
			if(v != null && u == null){
				usuarioService.guardar(usuarios);
				return ResponseEntity.status(HttpStatus.CREATED)
						.body("Usuario registrado.");
			}
		}
		catch (VecinoException ex){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}


		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body("bug");
    }

	//<editor-fold desc="cruzadas">

	//</editor-fold>





}
