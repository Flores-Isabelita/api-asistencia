package com.ib.asistencia.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
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
    @Column(name = "id_empresa")
    private String idEmpresa;
    @NotEmpty
    @Column(name = "nombre")
    private String nombre;

    @NotEmpty
    @Column(name = "cedula")
    private String cedula;


    @Column(name = "celular")
    private String celular;

    @NotEmpty
    @Column(name = "proceso")
    private String proceso;

    @NotEmpty
    @Column(name = "labor")
    private String labor;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Asistencia> asistencias;
}
