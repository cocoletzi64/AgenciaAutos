package com.mx.ApiRestAgenciaAutos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestAgenciaAutos.entity.Modelos;
import com.mx.ApiRestAgenciaAutos.repository.IModelosRepository;

public class ModelosService {
	@Autowired
	IModelosRepository modelosRepo;
	
	
@Transactional(readOnly=true)
public List<Modelos> mostar(){
	return modelosRepo.findAll();
}


@Transactional
public String guardar(Modelos modelo){
	for (Modelos m:mostar()) {
		if(m.getNombre().equalsIgnoreCase(modelo.getNombre()) && m.getId()==modelo.getId()) {
			return "Modelo existente";
		}
	}
		modelosRepo.save(modelo);
return "el modelo ha sido guardada correctamente ";

	}


@Transactional(readOnly=true)
public Modelos buscarXid(Integer id) {
return modelosRepo.findById(id).orElse(null);
	}

@Transactional
public boolean editar(Modelos modelo){
	Modelos modeloEncontr=modelosRepo.findById(modelo.getId()).orElse(modelo);
	if(modeloEncontr!=null) {
		modelosRepo.save(modelo);
		return true;
	}
	return false;

	}

@Transactional
public boolean eliminar(Integer id){
Modelos modeloEncontr=modelosRepo.findById(id).orElse(null);
	
	if(modeloEncontr!=null) {
		modelosRepo.deleteById(id);
		return true;
	}
return false;

	}
}
