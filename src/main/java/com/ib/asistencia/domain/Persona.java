package com.ib.asistencia.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@Data
@Entity
@Table(name="persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_empresa")
    private Long idEmpresa;

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

    @NotEmpty
    @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;

    //@JsonManagedReference
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
    private List<Asistencia> asistencias;

    // Método para obtener la última asistencia
    public Asistencia getUltimaAsistencia() {
        if (asistencias != null && !asistencias.isEmpty()) {
            return asistencias.get(asistencias.size() - 1);
        }
        return null; // Si no hay asistencias, devuelve null
    }

}
