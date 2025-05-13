package com.usc.test;

import com.usc.model.Comprador;
import com.usc.model.Orden;
import com.usc.model.Producto;
import com.usc.model.TIPO_BICICLETA;
import com.usc.model.TIPO_PRODUCTO;
import com.usc.model.Vendedor;
import com.usc.view.OrdenView;

import javax.swing.SwingUtilities;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Crear vendedor y productos 
        Vendedor vendedor = new Vendedor(
            "María García",
            "maria@bici-shop.com",
            "123456789",
            "Calle Principal 123",
            "V001"
        );

        Producto biciMontaña = new Producto(
            500.0, "Bicicleta de montaña profesional", "Trek", 5, TIPO_PRODUCTO.BICICLETA
        );
        biciMontaña.setTipoBicicleta(TIPO_BICICLETA.TODO_TERRENO);

        Producto casco = new Producto(
            50.0, "Casco de seguridad", "Bell", 15, TIPO_PRODUCTO.REPUESTO
        );

        // Anadir producto al inventario
        vendedor.agregarProducto(biciMontaña);
        vendedor.agregarProducto(casco);

        // Crear comprador 
        Comprador comprador = new Comprador(
            "Juan Pérez",
            "juan@email.com",
            "987654321",
            "Avenida Central 456",
            1000.0
        );

        // Crear la Orden y agregar productos 
        Orden orden = new Orden(comprador, vendedor);
        orden.agregarProducto(biciMontaña);
        orden.agregarProducto(casco);

   
        SwingUtilities.invokeLater(() -> {

            OrdenView view = new OrdenView(
                Arrays.asList(comprador),
                Arrays.asList(vendedor),
                orden.getNumeroOrden()
            );


            view.setVisible(true);
        });
    }
}
