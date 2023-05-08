package com.example.dineriumaplicacion.Clases;

public class Tareas {
    String Razon, Descripcion;
    Float Precio;

    public Tareas() {
        Razon = "nnn";
        Descripcion = "nnn";

    }

    public Tareas(String razon, String descripcion, Float precio) {
        Razon = razon;
        Descripcion = descripcion;
        Precio = precio;
    }

    public String getRazon() {
        return Razon;
    }

    public void setRazon(String razon) {
        Razon = razon;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Float getPrecio() {
        return Precio;
    }

    public void setPrecio(Float precio) {
        Precio = precio;
    }
}

