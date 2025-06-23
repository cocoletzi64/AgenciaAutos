package com.mx.ApiRestAgenciaAutos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApiRestAgenciaAutos.entity.Marcas;
import com.mx.ApiRestAgenciaAutos.service.MarcasService;

@RestController
@RequestMapping (path="Marcas")
@CrossOrigin
public class MarcasController {

	@Autowired
	MarcasService marcasServ;
	@GetMapping("/mostrar")
	public List<Marcas> mostrar(){
		return  marcasServ.mostar();
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<?>guardar(@RequestBody Marcas marca){
		try {
			String response=marcasServ.guardar(marca);
			if(response.equals("Ese registro no se puede guardar")) {
				return new ResponseEntity<>("No se pudo guardar el registro",
						HttpStatus.OK);}
				else {
					return new ResponseEntity<>(marca,HttpStatus.CREATED);
					}
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error al guardar"+e.getMessage(),HttpStatus.OK);
		}
	}
	@PostMapping("/buscarId/{id}")
	public Marcas buscar(@PathVariable("id") Integer id) {
		return marcasServ.buscarXid(id);
	}
	@PutMapping ("/editar")
	public ResponseEntity<?> editar(@RequestBody Marcas marca){
		try {
			boolean response=marcasServ.editar(marca);
			if(response==true) {
				return new ResponseEntity<>(marca,HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<>("Ese registro no existe",HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error al editar",HttpStatus.OK);
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
	    try {
	        boolean eliminado = marcasServ.eliminar(id);
	        if (eliminado) {
	            return new ResponseEntity<>("Se eliminó el registro", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("No existe registro para eliminar", HttpStatus.OK);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error al eliminar", HttpStatus.OK);
	    }
	}
}
