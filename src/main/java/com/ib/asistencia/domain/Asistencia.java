package com.ib.asistencia.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@Table(name = "asistencia")
public class Asistencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsistencia;

    @NotEmpty
    @Column(name = "fecha")
    private String fecha;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "estado")
    private String estado;

    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
    @JsonIgnoreProperties({"asistencias"})
    private Persona persona;
}
