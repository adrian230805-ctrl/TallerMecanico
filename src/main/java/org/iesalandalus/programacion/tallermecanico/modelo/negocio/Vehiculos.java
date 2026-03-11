package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class Vehiculos {
    private List<Vehiculo> coleccionVehiculos;

    public Vehiculos() {
        this.coleccionVehiculos = new ArrayList<>();
    }

    public List<Vehiculo> get() {
        return new ArrayList<>(this.coleccionVehiculos);
    }

    public void insertar(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("No se puede insertar un vehículo nulo.");
        }
        if (coleccionVehiculos.contains(vehiculo)) {
            throw new IllegalArgumentException("El vehículo con esa matrícula ya existe.");
        }
        coleccionVehiculos.add(vehiculo);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        if (vehiculo == null) return null;

        int indice = coleccionVehiculos.indexOf(vehiculo);
        if (indice != -1) {
            return coleccionVehiculos.get(indice);
        }
        return null;
    }

    public void borrar(Vehiculo vehiculo) {
        if (vehiculo == null || !coleccionVehiculos.remove(vehiculo)) {
            throw new IllegalArgumentException("Error al borrar: el vehículo no existe en la lista.");
        }
    }
}
