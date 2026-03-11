package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.util.Objects;

public class Revision {
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private double precioMaterial;

    private static final double PRECIO_HORA = 30.0;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        this.horas = 0;
        this.precioMaterial = 0.0;
    }

    public Revision(Revision otra) {
        if (otra == null) throw new IllegalArgumentException(("No se puede copiar una revisión nula."));
        this.cliente = new Cliente(otra.cliente);
        this.vehiculo = otra.vehiculo;
        this.fechaInicio = otra.fechaInicio;
        this.fechaFin = otra.fechaFin;
        this.horas = otra.horas;
        this.precioMaterial = otra.precioMaterial;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) throw new IllegalArgumentException("El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) throw new IllegalArgumentException("El vehiculo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio == null || fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a hoy.");
        }
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        if (fechaFin == null) throw new IllegalArgumentException("La fecha de fin no puede ser nula.");
        if (!fechaFin.isAfter(this.fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la de inicio.");
        }
        if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser posterior a hoy.");
        }
        this.fechaFin = fechaFin;
    }

    public void anadirHoras(int horas) {
        if (horas < 0) throw new IllegalArgumentException("Las horas a añadir no pueden ser negativas.");
        this.horas += horas;
    }

    public void anadirPrecioMaterial(double precioMaterial) {
        if (precioMaterial < 0) throw new IllegalArgumentException("El precio del material no puede ser negativo.");
        this.precioMaterial += precioMaterial;
    }

    public void cerrar(LocalDate fechaFin){
        setFechaFin(fechaFin);
    }

    public double getPrecio() {
        return (this.horas * PRECIO_HORA) + this.precioMaterial;
    }

    public Cliente getCliente() { return cliente; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return Objects.equals(cliente, revision.cliente) &&
                Objects.equals(vehiculo, revision.vehiculo) &&
                Objects.equals(fechaInicio, revision.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, vehiculo, fechaInicio);
    }

    @Override
    public String toString() {
        return String.format("Revision [cliente=%s, vehiculo=%s, fechaInicio=%s, fechaFin=%s, precio=%.2f]",
        cliente.getDni(), vehiculo.matricula(), fechaInicio,
                (fechaFin == null ? "en curso" : fechaFin), getPrecio());
    }
}
