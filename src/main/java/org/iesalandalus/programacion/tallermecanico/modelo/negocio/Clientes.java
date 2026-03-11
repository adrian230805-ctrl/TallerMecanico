package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import java.util.ArrayList;
import java.util.List;

public class Clientes {
    private List<Cliente> coleccionClientes;

    public Clientes() {
        this.coleccionClientes = new ArrayList<>();
    }

    public List<Cliente> get() {
        return new ArrayList<>(this.coleccionClientes);
    }

    public void insertar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("No se puede insertar un cliente nulo.");
        }
        if (coleccionClientes.contains(cliente)) {
            throw new IllegalArgumentException("El cliente ya existe en el sistema.");
        }
        coleccionClientes.add(cliente);
    }

    public Cliente modificar(Cliente cliente, String nuevoNombre, String nuevoTelefono) {
        Cliente encontrado = buscar(cliente);
        if (encontrado == null) {
            throw new IllegalArgumentException("El cliente a modificar no existe.");
        }

        if (nuevoNombre != null && !nuevoNombre.isBlank()) {
            encontrado.setNombre(nuevoNombre);
        }
        if (nuevoTelefono != null && !nuevoTelefono.isBlank()) {
            encontrado.setTelefono(nuevoTelefono);
        }

        return encontrado;
    }

    public Cliente buscar(Cliente cliente) {
        if (cliente == null) return null;

        int indice = coleccionClientes.indexOf(cliente);
        if (indice != -1) {
            return coleccionClientes.get(indice);
        }
        return null;
    }

    public void borrar(Cliente cliente) {
        if (cliente == null || !coleccionClientes.remove(cliente)) {
            throw new IllegalArgumentException("No se puede borrar: el cliente no existe.");
        }
    }
}
