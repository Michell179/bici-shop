
package com.usc.model;

public abstract class Persona {
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

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
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
