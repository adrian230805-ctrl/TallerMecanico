package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;

    public void comenzar() {
        this.clientes = new Clientes();
        this.vehiculos = new Vehiculos();
        this.revisiones = new Revisiones();
    }

    public void terminar() {
        System.out.println("El modelo de datos ha finalizado correctamente.");
    }


    public void insertar(Cliente cliente) {
        clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) {
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) {
        Cliente cExistente = clientes.buscar(revision.getCliente());
        Vehiculo vExistente = vehiculos.buscar(revision.getVehiculo());

        if (cExistente == null || vExistente == null) {
            throw new IllegalArgumentException("No se puede crear la revisión: Cliente o Vehículo no registrados.");
        }

        revisiones.insertar(new Revision(cExistente, vExistente, revision.getFechaInicio()));
    }


    public Cliente buscar(Cliente cliente) {
        Cliente encontrado = clientes.buscar(cliente);
        return (encontrado != null) ? new Cliente(encontrado) : null;
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo); // Inmutable
    }

    public Revision buscar(Revision revision) {
        Revision encontrada = revisiones.buscar(revision);
        return (encontrada != null) ? new Revision(encontrada) : null;
    }


    public void modificar(Cliente cliente, String nombre, String telefono) {
        clientes.modificar(cliente, nombre, telefono);
    }

    public void anadirHoras(Revision revision, int horas) {
        revisiones.anadirHoras(revision, horas);
    }

    public void anadirPrecioMaterial(Revision revision, double precio) {
        revisiones.anadirPrecioMaterial(revision, precio);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) {
        revisiones.cerrar(revision, fechaFin);
    }


    public void borrar(Cliente cliente) {
        List<Revision> lista = revisiones.get(cliente);
        for (Revision r : lista) {
            revisiones.borrar(r);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) {
        List<Revision> lista = revisiones.get(vehiculo);
        for (Revision r : lista) {
            revisiones.borrar(r);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Revision revision) {
        revisiones.borrar(revision);
    }


    public List<Cliente> getClientes() {
        List<Cliente> copia = new ArrayList<>();
        for (Cliente c : clientes.get()) {
            copia.add(new Cliente(c));
        }
        return copia;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos.get();
    }

    public List<Revision> getRevisiones() {
        List<Revision> copia = new ArrayList<>();
        for (Revision r : revisiones.get()) {
            copia.add(new Revision(r));
        }
        return copia;
    }
}
