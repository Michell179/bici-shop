package com.usc.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Producto {
    // Atributos (características del producto)
    private double precio; // Precio del producto
    private String descripcion; // Descripción detallada
    private String marca; // Marca del producto
    private int cantidad; // Cantidad en inventario
    private TIPO_PRODUCTO tipoProducto; // Tipo de producto (BICICLETA o REPUESTO)
    private TIPO_BICICLETA tipoBicicleta; // Tipo de bicicleta (si aplica)

    // Método especial para crear objetos
    public Producto(double precio, String descripcion, String marca, int cantidad, TIPO_PRODUCTO tipoProducto) {
        this.precio = precio;
        this.descripcion = descripcion;
        this.marca = marca;
        this.cantidad = cantidad;
        this.tipoProducto = tipoProducto;
        this.tipoBicicleta = null; // Inicialmente nulo hasta que se defina
    }

    public void setTipoBicicleta(TIPO_BICICLETA tipoBicicleta) {
        if (this.tipoProducto.equals(TIPO_PRODUCTO.BICICLETA)) {
            this.tipoBicicleta = tipoBicicleta;
        } else {
            System.out.println("Error: Solo se puede asignar tipo de bicicleta a productos de tipo BICICLETA");
        }
    }

    // Método para mostrar la información del producto sobre escrito en el metodo
    // del objeto
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo de producto: ").append(tipoProducto).append("\n");
        sb.append("Descripción: ").append(descripcion).append("\n");
        sb.append("Marca: ").append(marca).append("\n");
        if (tipoProducto == TIPO_PRODUCTO.BICICLETA && tipoBicicleta != null) {
            sb.append("Tipo de bicicleta: ").append(tipoBicicleta).append("\n");
        }
        sb.append("Precio: $").append(precio).append("\n");
        sb.append("Cantidad disponible: ").append(cantidad);
        return sb.toString();
    }

    // Muestra la información del producto, igual ya manejamos el la reescritura con
    // tostring
    public void mostrarInformacion() {
        System.out.println(this.toString());
    }

    // Verifica si hay suficiente stock disponible
    public boolean hayStockDisponible(int cantidadRequerida) {
        return cantidad >= cantidadRequerida;
    }

    // Actualiza el stock (aumenta o disminuye)
    public void actualizarStock(int cantidad) {
        if (this.cantidad + cantidad >= 0) {
            this.cantidad += cantidad;
        } else {
            System.out.println("Error: No se puede tener stock negativo");
        }
    }

    // Calcula el precio con descuento
    public double calcularPrecioConDescuento(double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            System.out.println("Error: El porcentaje de descuento debe estar entre 0 y 100");
            return precio;
        }
        return precio * (1 - porcentajeDescuento / 100);
    }

    // Compara dos productos por precio
    public int compararPorPrecio(Producto otroProducto) {
        return Double.compare(this.precio, otroProducto.getPrecio());
    }

    // Verifica si dos productos son iguales (misma descripción y marca)
    public boolean esIgual(Producto otroProducto) {
        return this.descripcion.equals(otroProducto.getDescripcion()) &&
                this.marca.equals(otroProducto.getMarca());
    }

    // Muestra el estado de disponibilidad del producto
    public String mostrarDisponibilidad() {
        if (cantidad == 0) {
            return "Producto agotado";
        } else if (cantidad <= 5) {
            return "Últimas " + cantidad + " unidades disponibles";
        } else if (cantidad <= 10) {
            return "Quedan " + cantidad + " unidades";
        } else {
            return "Disponible (" + cantidad + " unidades)";
        }
    }
}
