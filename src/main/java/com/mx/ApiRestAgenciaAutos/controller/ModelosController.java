package com.mx.ApiRestAgenciaAutos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mx.ApiRestAgenciaAutos.entity.Modelos;
import com.mx.ApiRestAgenciaAutos.service.ModelosService;

public class ModelosController {

	@Autowired
	ModelosService modelosServ;
	@GetMapping("/mostrar")
	public List<Modelos> mostrar(){
		return  modelosServ.mostar();
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<?>guardar(@RequestBody Modelos modelo){
		try {
			String response=modelosServ.guardar(modelo);
			if(response.equals("Ese registro no se puede guardar")) {
				return new ResponseEntity<>("No se pudo guardar el registro",
						HttpStatus.OK);}
				else {
					return new ResponseEntity<>(modelo,HttpStatus.CREATED);
					}
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error al guardar"+e.getMessage(),HttpStatus.OK);
		}
	}
	@PostMapping("/buscarId/{id}")
	public Modelos buscar(@PathVariable("id") Integer id) {
		return modelosServ.buscarXid(id);
	}
	@PutMapping ("/editar")
	public ResponseEntity<?> editar(@RequestBody Modelos modelo){
		try {
			boolean response=modelosServ.editar(modelo);
			if(response==true) {
				return new ResponseEntity<>(modelo,HttpStatus.CREATED);
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
	        boolean eliminado = modelosServ.eliminar(id);
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
