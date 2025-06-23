package com.mx.ApiRestAgenciaAutos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestAgenciaAutos.entity.Marcas;
import com.mx.ApiRestAgenciaAutos.repository.IMarcasRepository;



@Service
public class MarcasService {
	@Autowired
	IMarcasRepository marcasRepo;
	
	
@Transactional(readOnly=true)
public List<Marcas> mostar(){
	return marcasRepo.findAll();
}

//validar que el id y el nombre no se repita 
@Transactional
public String guardar(Marcas marca){
	for (Marcas m:mostar()) {
		if(m.getNombre().equalsIgnoreCase(marca.getNombre()) && m.getId()==marca.getId()) {
			return "Marca existente";
		}
	}
		marcasRepo.save(marca);
return "la marca a sido guardada correctamente ";

	}


@Transactional(readOnly=true)
public Marcas buscarXid(Integer id) {
return marcasRepo.findById(id).orElse(null);
	}

@Transactional
public boolean editar(Marcas marca){
	Marcas marcaEncontr=marcasRepo.findById(marca.getId()).orElse(marca);
	if(marcaEncontr!=null) {
		marcasRepo.save(marca);
		return true;
	}
	return false;

	}

@Transactional
public boolean eliminar(Integer id){
Marcas marcaEncontr=marcasRepo.findById(id).orElse(null);
	
	if(marcaEncontr!=null) {
		marcasRepo.deleteById(id);
		return true;
	}
return false;

	}


}
