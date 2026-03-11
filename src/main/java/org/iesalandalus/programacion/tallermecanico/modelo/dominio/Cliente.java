package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.regex.Pattern;
import java.util.Objects;

public class Cliente {
    public static final String ER_NOMBRE = "^([A-ZÑÁÉÍÓÚ][a-zñáéíóú]+\\s?)+$";
    public static final String ER_DNI = "^([0-9]{8})([TRWAGMYFPDXBNJZSQVHLCKE])$";
    public static final String ER_TELEFONO = "^[6789]\\d{8}$";

    private String nombre;
    private String dni;
    private String telefono;

    public Cliente(String nombre, String dni, String telefono) {
        setDni(dni);
        setNombre(nombre);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente) {
        if (cliente == null) throw new IllegalArgumentException("No se puede copiar un cliente nulo.");
        this.nombre = cliente.nombre;
        this.dni = cliente.dni;
        this.telefono = cliente.telefono;
    }

    public static Cliente getClienteValido(String dni) {
        return new Cliente("Nombre Valido", dni, "600000000");
    }
    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    public String getTelefono() { return telefono; }

    public void setNombre(String nombre) {
        if (nombre == null || !Pattern.matches(ER_NOMBRE, nombre.trim())) {
            throw new IllegalArgumentException("Nombre no válido: Cada palabra debe empezar con mayúscula.");
        }
        this.nombre = nombre.trim();
    }

    public void setDni(String dni) {
        if (!validarDni(dni)) {
            throw new IllegalArgumentException("DNI no válido o letra incorrecta.");
        }
        this.dni = dni.toUpperCase();
    }

    public void setTelefono(String telefono) {
        if (telefono == null || !Pattern.matches(ER_TELEFONO, telefono)) {
            throw new IllegalArgumentException("Teléfono no válido: Debe tener 9 dígitos y empezar por 6, 7, 8 o 9.");
        }
        this.telefono = telefono;
    }

    private boolean validarDni(String dni) {
        if (dni == null || !Pattern.matches(ER_DNI, dni.toUpperCase())) return false;
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(dni.substring(0, 8));
        char letraCalculada = letras.charAt(numero % 23);
        char letraProporcionada = dni.toUpperCase().charAt(8);
        return letraCalculada == letraProporcionada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return String.format("Cliente [nombre=%s, dni=%s, telefono=%s]", nombre, dni, telefono);
    }
}
