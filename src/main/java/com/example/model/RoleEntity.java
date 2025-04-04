package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;

    // Constructor privado para el Builder
    private RoleEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    // Constructor vacío (necesario para JPA)
    public RoleEntity() {}

    // Getters
    public Long getId() {
        return id;
    }

    public ERole getName() {
        return name;
    }

    // Clase estática Builder
    public static class Builder {
        private Long id;
        private ERole name;

        // Métodos para establecer los valores de los campos
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(ERole name) {
            this.name = name;
            return this;
        }

        // Método para construir la instancia de RoleEntity
        public RoleEntity build() {
            return new RoleEntity(this);
        }
    }

    // Método estático para iniciar el Builder
    public static Builder builder() {
        return new Builder();
    }
}
