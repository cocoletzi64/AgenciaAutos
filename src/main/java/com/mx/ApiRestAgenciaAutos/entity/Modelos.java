package com.mx.ApiRestAgenciaAutos.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table (name = "MODELOS_19")
public class Modelos {
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="PLACA")
	private String placa;
	
	@Column(name="PRECIO")
	private Float precio;
	
//CARDINALIDAD O RELACION ENTRE ENTIDADES
//INDICA QUE LA RELACION DEBE SER CARGADA AL MOMENTO
	@ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn(name="FK_MARCA")
	private Marcas marca; //ESTA VAR DEBE SER IGUAL A LO QUE PUSIERON EN EL MAPPED BY
	
}
