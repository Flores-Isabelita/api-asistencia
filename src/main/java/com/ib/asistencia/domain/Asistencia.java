package com.ib.asistencia.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

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
    private LocalDate fecha;

    @NotEmpty
    @Column(name = "observacion")
    private String observacion;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

}
