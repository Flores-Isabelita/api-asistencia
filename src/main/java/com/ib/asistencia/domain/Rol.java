package com.ib.asistencia.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="rol")
public class Rol implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    @NotEmpty
    private String nombre;

    public Rol(){
    }

    public Rol(String nombre) {
        super();
        this.nombre = nombre;
    }

    private static final long serialVersionUID = 1L;

}
