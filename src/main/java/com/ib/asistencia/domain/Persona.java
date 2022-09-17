package com.ib.asistencia.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;


@Data
@Entity
@Table(name="persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;
    
    @NotEmpty
    private String idEmpresa;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String cedula;

    private String celular;

    @NotEmpty
    private String proceso;
    
    private String estado;
    
    private String observaciones;

}
