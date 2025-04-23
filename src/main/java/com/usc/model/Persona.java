package com.usc.model;

abstract class Persona {

    private String nombre;
    private String email;
    private String telefono;


    public Persona(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }
}
