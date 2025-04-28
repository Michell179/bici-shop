
package com.usc.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Persona {
    // Getters y Setters
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;

    public Persona(String nombre, String email, String telefono, String direccion) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Método para mostrar información básica
    public String mostrarInformacionBasica() {
        return "Nombre: " + nombre + "\n" +
                "Email: " + email + "\n" +
                "Teléfono: " + telefono + "\n" +
                "Dirección: " + direccion;
    }

    // Método abstracto para mostrar información específica del tipo de persona
    public abstract String mostrarInformacionEspecifica();
}
