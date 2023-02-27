package com.ib.asistencia.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name="usuario")
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="id_usuario")
    private List<Rol> roles;
 }

/*
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
    private Rol rol;
}
 */
