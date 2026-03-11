package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Revisiones {
    private List<Revision> coleccionRevisiones;

    public Revisiones() {
        this.coleccionRevisiones = new ArrayList<>();
    }

    public List<Revision> get() {
        return new ArrayList<>(this.coleccionRevisiones);
    }

    public List<Revision> get(Cliente cliente) {
        List<Revision> listaCliente = new ArrayList<>();
        for (Revision r : coleccionRevisiones) {
            if (r.getCliente().equals(cliente)) listaCliente.add(r);
        }
        return listaCliente;
    }

    public List<Revision> get(Vehiculo vehiculo) {
        List<Revision> listaVehiculo = new ArrayList<>();
        for (Revision r : coleccionRevisiones) {
            if (r.getVehiculo().equals(vehiculo)) listaVehiculo.add(r);
        }
        return listaVehiculo;
    }

    public void insertar(Revision revision) {
        if (revision == null) throw new IllegalArgumentException("No se puede insertar una revisión nula.");
        comprobarRevision(revision); // Lanza excepción si no es válida
        coleccionRevisiones.add(revision);
    }

    public void comprobarRevision(Revision nueva) {
        for (Revision r : coleccionRevisiones) {
            if (r.getFechaFin() == null) {
                if (r.getCliente().equals(nueva.getCliente()))
                    throw new IllegalArgumentException("El cliente ya tiene una revisión abierta.");
                if (r.getVehiculo().equals(nueva.getVehiculo()))
                    throw new IllegalArgumentException("El vehículo ya tiene una revisión abierta.");
            } else {
                if (r.getCliente().equals(nueva.getCliente()) && r.getFechaFin().isAfter(nueva.getFechaInicio()))
                    throw new IllegalArgumentException("El cliente tiene una revisión posterior a la fecha de inicio.");
                if (r.getVehiculo().equals(nueva.getVehiculo()) && r.getFechaFin().isAfter(nueva.getFechaInicio()))
                    throw new IllegalArgumentException("El vehículo tiene una revisión posterior a la fecha de inicio.");
            }
        }
    }

    public Revision getRevision(Revision revision) {
        if (revision == null) throw new IllegalArgumentException("La revisión no puede ser nula.");
        int indice = coleccionRevisiones.indexOf(revision);
        if (indice == -1) throw new IllegalArgumentException("La revisión no existe en el sistema.");
        return coleccionRevisiones.get(indice);
    }

    public Revision anadirHoras(Revision revision, int horas) {
        Revision encontrada = getRevision(revision);
        encontrada.anadirHoras(horas);
        return encontrada;
    }

    public Revision anadirPrecioMaterial(Revision revision, double precio) {
        Revision encontrada = getRevision(revision);
        encontrada.anadirPrecioMaterial(precio);
        return encontrada;
    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) {
        Revision encontrada = getRevision(revision);
        encontrada.cerrar(fechaFin);
        return encontrada;
    }

    public Revision buscar(Revision revision) {
        if (revision == null) return null;
        int indice = coleccionRevisiones.indexOf(revision);
        return (indice != -1) ? coleccionRevisiones.get(indice) : null;
    }

    public void borrar(Revision revision) {
        if (revision == null || !coleccionRevisiones.remove(revision)) {
            throw new IllegalArgumentException("No se puede borrar: la revisión no existe.");
        }
    }
}

