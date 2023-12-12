package com.nico.manejodeerrores.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombres")
    @NotEmpty
    @Size(min = 2, max = 22)
    private String nombre;
    @Column(name = "apellidos")
    @NotEmpty
    @Size(min = 2, max = 22)
    private String apellido;
}
