package com.coder.desafio3;

import java.util.Objects;

public class ClienteEdadCalculada {

    private String nombre;
    private String apellido;
    private int edad;

    public ClienteEdadCalculada() {
    }

    public ClienteEdadCalculada(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ClienteEdadCalculada nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public ClienteEdadCalculada apellido(String apellido) {
        setApellido(apellido);
        return this;
    }

    public ClienteEdadCalculada edad(int edad) {
        setEdad(edad);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ClienteEdadCalculada)) {
            return false;
        }
        ClienteEdadCalculada clienteEdadCalculada = (ClienteEdadCalculada) o;
        return Objects.equals(nombre, clienteEdadCalculada.nombre)
                && Objects.equals(apellido, clienteEdadCalculada.apellido) && edad == clienteEdadCalculada.edad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, edad);
    }

    @Override
    public String toString() {
        return "{" +
                " nombre='" + getNombre() + "'" +
                ", apellido='" + getApellido() + "'" +
                ", edad='" + getEdad() + "'" +
                "}";
    }

}
