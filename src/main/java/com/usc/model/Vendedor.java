package com.usc.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

// 1. Declaración de la clase y herencia
@Getter
public class Vendedor extends Persona {
    // Obtiene el inventario completo
    // 2. Atributos específicos del vendedor
    private List<Producto> inventario; // Lista de productos en venta
    // Obtiene las ventas totales
    private double ventasTotales; // Registro de todas las ventas
    // Obtiene el código del vendedor
    private String codigoVendedor; // Identificador único del vendedor

    // 3. Constructor
    public Vendedor(String nombre, String email, String telefono, String direccion, String codigoVendedor) {
        // Llama al constructor de la clase padre (Persona)
        super(nombre, email, telefono, direccion);
        // Inicializa el inventario como lista vacía
        this.inventario = new ArrayList<>();
        // Inicializa las ventas en 0
        this.ventasTotales = 0;
        // Asigna el código del vendedor
        this.codigoVendedor = codigoVendedor;
    }

    // 4. Métodos para gestionar el inventario
    // Agrega un producto al inventario
    public void agregarProducto(Producto producto) {
        inventario.add(producto);
    }

    // Elimina un producto del inventario
    public void eliminarProducto(Producto producto) {
        inventario.remove(producto);
    }

    // 5. Métodos para gestionar ventas
    // Procesa una venta con un comprador
    public boolean procesarVenta(Comprador comprador) {
        if (comprador.puedeComprar()) { // Verifica si el comprador puede pagar
            double totalVenta = comprador.calcularTotalCarrito();
            ventasTotales += totalVenta; // Actualiza las ventas totales
            comprador.realizarCompra(); // Realiza la compra
            return true;
        }
        return false;
    }

    // Modifica el código del vendedor
    public void setCodigoVendedor(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    // 7. Implementación del método abstracto de Persona
    @Override
    public String mostrarInformacionEspecifica() {
        return "Tipo: Vendedor\n" +
                "Código de vendedor: " + codigoVendedor + "\n" +
                "Productos en inventario: " + inventario.size() + "\n" +
                "Ventas totales: $" + ventasTotales;
    }
}