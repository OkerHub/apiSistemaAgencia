package com.mx.sistemaAgenciaAutos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.sistemaAgenciaAutos.model.Marcas;
import com.mx.sistemaAgenciaAutos.service.MarcasImp;

@RestController
@RequestMapping(path = "MarcasWs")
@CrossOrigin
public class MarcasWs {
	@Autowired
	MarcasImp marcaImp;

	// http://localhost:9000/MarcasWs/listar
	@GetMapping(path = "listar")
	public List<Marcas> listar() {
		return marcaImp.listar();
	}

	// http://localhost:9000/MarcasWs/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Marcas marca) {
		String response = marcaImp.guardar(marca);

		if (response.equals("idExiste"))
			return new ResponseEntity<>("Ese id ya existe", HttpStatus.OK);
		else if (response.equals("nombrExiste"))
			return new ResponseEntity<>("Ese nombre ya existe", HttpStatus.OK);
		else
			return new ResponseEntity<>(marca, HttpStatus.CREATED);
	}

	// http://localhost:9000/MarcasWs/buscar
	@PostMapping(path = "buscar")
	public Marcas buscar(@RequestBody Marcas marca) {
		return marcaImp.buscar(marca.getId());
	}

	// http://localhost:9000/MarcasWs/editar
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Marcas marca) {
		boolean response = marcaImp.editar(marca);

		if (response == false)
			return new ResponseEntity<>("Ese registro no existe", HttpStatus.OK);
		else
			return new ResponseEntity<>(marca, HttpStatus.CREATED);
	}

	// http://localhost:9000/MarcasWs/eliminar
	@PostMapping(path = "eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Marcas marca) {
		boolean response = marcaImp.eliminar(marca.getId());

		if (response == false)
			return new ResponseEntity<>("Ese registro no existe", HttpStatus.OK);
		else
			return new ResponseEntity<>("Se elimino con exito", HttpStatus.OK);
	}

}
