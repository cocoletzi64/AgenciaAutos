package com.mx.ApiRestAgenciaAutos.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name = "MARCAS_19")
public class Marcas {
	@Id
	@Column (name ="ID")
	private Integer id;
	
	@Column (name ="NOMBRE")
	private String nombre;
	
	@Column (name ="ORIGEN")
	private String origen;
	
	@Column (name ="FECHA_LANZ")
	private Date fechaLanz;
	
	//Cardinalidad
	@OneToMany (mappedBy = "marca",cascade =CascadeType.ALL)
	@JsonIgnore //para omitir una lista de propiedades 
	private List<Modelos> lista=new ArrayList<Modelos>();
	

}
