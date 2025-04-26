package com.usc;

import com.usc.model.Comprador;
import com.usc.model.Orden;
import com.usc.model.Producto;
import com.usc.model.TIPO_BICICLETA;
import com.usc.model.TIPO_PRODUCTO;
import com.usc.model.Vendedor;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== BIENVENIDO A BICI-SHOP ===");
        System.out.println("Inicializando sistema...\n");

        // 1. Crear vendedor
        Vendedor vendedor = new Vendedor(
                "María García",
                "maria@bici-shop.com",
                "123456789",
                "Calle Principal 123",
                "V001");
        System.out.println("Vendedor creado:");
        System.out.println(vendedor.mostrarInformacionBasica());
        System.out.println(vendedor.mostrarInformacionEspecifica());
        System.out.println();

        // 2. Crear productos
        Producto bicicletaMontana = new Producto(
                500.0,
                "Bicicleta de montaña profesional",
                "Trek",
                5,
                TIPO_PRODUCTO.BICICLETA);
        bicicletaMontana.setTipoBicicleta(TIPO_BICICLETA.TODO_TERRENO);

        Producto bicicletaCiudad = new Producto(
                300.0,
                "Bicicleta urbana",
                "Giant",
                8,
                TIPO_PRODUCTO.BICICLETA);
        bicicletaCiudad.setTipoBicicleta(TIPO_BICICLETA.CIUDAD);

        Producto casco = new Producto(
                50.0,
                "Casco de seguridad",
                "Bell",
                15,
                TIPO_PRODUCTO.REPUESTO);

        Producto luces = new Producto(
                30.0,
                "Kit de luces LED",
                "Cateye",
                20,
                TIPO_PRODUCTO.REPUESTO);

        // 3. Agregar productos al inventario del vendedor
        vendedor.agregarProducto(bicicletaMontana);
        vendedor.agregarProducto(bicicletaCiudad);
        vendedor.agregarProducto(casco);
        vendedor.agregarProducto(luces);

        System.out.println("Productos agregados al inventario:");
        for (Producto producto : vendedor.getInventario()) {
            System.out.println("- " + producto.getDescripcion() +
                    " (" + producto.mostrarDisponibilidad() + ")");
        }
        System.out.println();

        // 4. Crear comprador
        Comprador comprador = new Comprador(
                "Juan Pérez",
                "juan@email.com",
                "987654321",
                "Avenida Central 456",
                1000.0);
        System.out.println("Comprador creado:");
        System.out.println(comprador.mostrarInformacionBasica());
        System.out.println(comprador.mostrarInformacionEspecifica());
        System.out.println();

        // 5. Comprador agrega productos al carrito
        System.out.println("Comprador agregando productos al carrito...");
        comprador.agregarAlCarrito(bicicletaMontana);
        comprador.agregarAlCarrito(casco);
        comprador.agregarAlCarrito(luces);

        System.out.println("Productos en carrito:");
        for (Producto producto : comprador.getCarritoCompras()) {
            System.out.println("- " + producto.getDescripcion() +
                    " ($" + producto.getPrecio() + ")");
        }
        System.out.println("Total carrito: $" + comprador.calcularTotalCarrito());
        System.out.println();

        // 6. Crear y procesar orden
        System.out.println("Procesando orden...");
        Orden orden = new Orden(comprador, vendedor);
        orden.agregarProducto(bicicletaMontana);
        orden.agregarProducto(casco);
        orden.agregarProducto(luces);

        if (orden.procesarOrden()) {
            System.out.println("¡Orden procesada con éxito!");
            System.out.println("\nResumen de la orden:");
            System.out.println(orden.toString());
        } else {
            System.out.println("No se pudo procesar la orden");
        }

        // 7. Mostrar estado final
        System.out.println("\nEstado final:");
        System.out.println("Saldo del comprador: $" + comprador.getSaldoDisponible());
        System.out.println("Ventas totales del vendedor: $" + vendedor.getVentasTotales());

        System.out.println("\nInventario actualizado:");
        for (Producto producto : vendedor.getInventario()) {
            System.out.println("- " + producto.getDescripcion() +
                    " (" + producto.mostrarDisponibilidad() + ")");
        }
    }
}