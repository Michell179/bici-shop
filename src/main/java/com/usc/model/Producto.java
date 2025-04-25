package com.usc.model;

public class Producto {
    //Atributos (características del producto)
    private double precio; //Precio del producto
    private String descripcion; //Descripción detallada
    private String marca; //Marca del producto
    private int cantidad; //Cantidad en inventario
    
    //Método especial para crear objetos
    public Producto (double precio, String descripcion, String marca, int cantidad)
    {
        this.precio = precio;
        this.descripcion=descripcion;
        this.marca = marca;
        this.cantidad = cantidad;
  }
  
    // Para acceder y modificar los atributos (encapsulamiento)
    public double getPrecio () {
        return precio;
    }
    
    public void setPrecio (double precio){
        this.precio = precio;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getMarca(){
        return marca;
    }
    
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    public int getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    //Muestra la información del prodcuto
    public void mostrarInformacion(){
        System.out.println("Descripción: " + descripcion);
        System.out.println("Descripción: " + marca);
        System.out.println("Precio: $" + precio);
        System.out.println("Cantidad disponible: " + cantidad);
    }
                
}
