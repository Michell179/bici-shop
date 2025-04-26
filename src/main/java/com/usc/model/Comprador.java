package com.usc.model;

import java.util.ArrayList;
import java.util.List;

// 1. Declaración de la clase y herencia
public class Comprador extends Persona {
    // 2. Atributos específicos del comprador
    private List<Producto> carritoCompras; // Lista para guardar productos
    private double saldoDisponible; // Dinero disponible

    // 3. Constructor
    public Comprador(String nombre, String email, String telefono, String direccion, double saldoDisponible) {
        // Llama al constructor de la clase padre (Persona)
        super(nombre, email, telefono, direccion);
        // Inicializa el carrito como una lista vacía
        this.carritoCompras = new ArrayList<>();
        // Asigna el saldo inicial
        this.saldoDisponible = saldoDisponible;
    }

    // 4. Métodos para gestionar el carrito
    // Agrega un producto al carrito
    public void agregarAlCarrito(Producto producto) {
        carritoCompras.add(producto);
    }

    // Elimina un producto del carrito
    public void eliminarDelCarrito(Producto producto) {
        carritoCompras.remove(producto);
    }

    // Vacía todo el carrito
    public void vaciarCarrito() {
        carritoCompras.clear();
    }

    // 5. Cálculo del total del carrito
    public double calcularTotalCarrito() {
        // Usamos streams para sumar los precios de todos los productos
        // convierte la lista en un "flujo" de datos Permite procesar los elementos uno
        // por uno.
        return carritoCompras.stream()
                // con map transformo cada elemento, y Producto::getPrecio es una referencia al
                // método que obtiene el precio
                .mapToDouble(Producto::getPrecio)
                // sumo todos los precios y retorno
                .sum();
    }

    // 6. Verificación de capacidad de compra
    public boolean puedeComprar() {
        // Verifica si el saldo es suficiente para el total del carrito
        return saldoDisponible >= calcularTotalCarrito();
    }

    // 7. Realización de la compra
    public boolean realizarCompra() {
        if (puedeComprar()) { // Si puede comprar
            saldoDisponible -= calcularTotalCarrito(); // Resta el total
            vaciarCarrito(); // Vacía el carrito
            return true; // Indica éxito
        }
        return false; // Indica fallo
    }

    // 8. Getters y Setters
    // Obtiene la lista de productos en el carrito
    /*
     * el carrito de compras se modifica a través de métodos
     * específicos que controlan mejor cómo se modifican los productos.
     * En lugar de un setter, tenemos estos métodos específicos:
     * como son agregarAlCarrito, eliminarDelCarrito y vaciarCarrito.
     */
    public List<Producto> getCarritoCompras() {
        return this.carritoCompras;
    }

    // Obtiene el saldo disponible
    public double getSaldoDisponible() {
        return this.saldoDisponible;
    }

    // Modifica el saldo disponible
    public void setSaldoDisponible(double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    // 9. Implementación del método abstracto de Persona
    @Override
    public String mostrarInformacionEspecifica() {
        return "Tipo: Comprador\n" +
                "Saldo disponible: $" + saldoDisponible + "\n" +
                "Productos en carrito: " + carritoCompras.size() + "\n" +
                "Total carrito: $" + calcularTotalCarrito();
    }
}