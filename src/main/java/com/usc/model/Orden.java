package com.usc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Orden {
    private String numeroOrden;
    private Comprador comprador;
    private Vendedor vendedor;
    private List<Producto> productos;
    private double total;
    private Date fecha;
    private String estado; // PENDIENTE, COMPLETADA, CANCELADA

    public Orden(Comprador comprador, Vendedor vendedor) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.productos = new ArrayList<>();
        this.fecha = new Date();
        this.estado = "PENDIENTE";
        this.numeroOrden = generarNumeroOrden();
    }

    private String generarNumeroOrden() {
        return "ORD-" + System.currentTimeMillis();
    }

    // Métodos para gestionar productos en la orden
    public void agregarProducto(Producto producto) {
        if (producto.hayStockDisponible(1)) {
            productos.add(producto);
            calcularTotal();
        } else {
            System.out.println("No hay stock disponible para: " + producto.getDescripcion());
        }
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
        calcularTotal();
    }

    private void calcularTotal() {
        total = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
    }

    // Procesar la orden
    public boolean procesarOrden() {
        if (estado.equals("PENDIENTE") && comprador.puedeComprar()) {
            // Verificar stock de todos los productos
            for (Producto producto : productos) {
                if (!producto.hayStockDisponible(1)) {
                    System.out.println("No hay suficiente stock para: " + producto.getDescripcion());
                    return false;
                }
            }

            // Procesar la compra
            comprador.realizarCompra();
            for (Producto producto : productos) {
                producto.actualizarStock(-1);
            }
            vendedor.procesarVenta(comprador);
            estado = "COMPLETADA";
            return true;
        }
        return false;
    }

    // Cancelar la orden
    public void cancelarOrden() {
        if (estado.equals("PENDIENTE")) {
            estado = "CANCELADA";
            productos.clear();
            calcularTotal();
        }
    }

    // Getters
    public String getNumeroOrden() {
        return numeroOrden;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public double getTotal() {
        return total;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    // Mostrar información de la orden
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Número de Orden: ").append(numeroOrden).append("\n");
        sb.append("Fecha: ").append(fecha).append("\n");
        sb.append("Estado: ").append(estado).append("\n");
        sb.append("Comprador: ").append(comprador.getNombre()).append("\n");
        sb.append("Vendedor: ").append(vendedor.getNombre()).append("\n");
        sb.append("Productos:\n");
        for (Producto producto : productos) {
            sb.append("- ").append(producto.getDescripcion())
                    .append(" ($").append(producto.getPrecio()).append(")\n");
        }
        sb.append("Total: $").append(total);
        return sb.toString();
    }
}