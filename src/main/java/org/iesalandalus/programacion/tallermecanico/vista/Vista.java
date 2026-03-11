package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.List;

public class Vista {

    private Controlador controlador;

    public Vista() {}

    public void setControlador(Controlador controlador) {
        if (controlador != null) {
            this.controlador = controlador;
        }
    }

    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("Saliendo de la aplicación... ¡Hasta pronto!");
    }

    private void ejecutar(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_CLIENTE -> insertarCliente();
            case BUSCAR_CLIENTE -> buscarCliente();
            case BORRAR_CLIENTE -> borrarCliente();
            case INSERTAR_VEHICULO -> insertarVehiculo();
            case BUSCAR_VEHICULO -> buscarVehiculo();
            case BORRAR_VEHICULO -> borrarVehiculo();
            case INSERTAR_REVISION -> insertarRevision();
            case CERRAR_REVISION -> cerrarRevision();
            case LISTAR_TODO -> listarTodo();
            case SALIR -> terminar();
        }
    }


    private void insertarCliente() {
        Consola.mostrarCabecera("Insertar Nuevo Cliente");
        try {
            String nombre = Consola.leerCadena("Nombre: ");
            String dni = Consola.leerCadena("DNI: ");
            String tlf = Consola.leerCadena("Teléfono: ");
            controlador.insertar(new Cliente(nombre, dni, tlf));
            System.out.println("Cliente insertado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void buscarCliente() {
        Consola.mostrarCabecera("Buscar Cliente");
        String dni = Consola.leerCadena("Introduce el DNI del cliente: ");
        Cliente encontrado = controlador.buscar(Cliente.getClienteValido(dni));
        if (encontrado != null) {
            System.out.println("Datos: " + encontrado);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void borrarCliente() {
        Consola.mostrarCabecera("Borrar Cliente");
        try {
            String dni = Consola.leerCadena("DNI del cliente a borrar: ");
            controlador.borrar(Cliente.getClienteValido(dni));
            System.out.println("Cliente y sus revisiones eliminados.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private void insertarVehiculo() {
        Consola.mostrarCabecera("Insertar Vehículo");
        try {
            String marca = Consola.leerCadena("Marca (Seat, Land Rover, KIA, Rolls-Royce, SsangYong): ");
            String modelo = Consola.leerCadena("Modelo: ");
            String matricula = Consola.leerCadena("Matrícula (1111BBB): ");
            controlador.insertar(new Vehiculo(marca, modelo, matricula));
            System.out.println("Vehículo registrado.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void buscarVehiculo() {
        Consola.mostrarCabecera("Buscar Vehículo");
        String matricula = Consola.leerCadena("Matrícula: ");
        Vehiculo v = controlador.buscar(Vehiculo.getVehiculoValido(matricula));
        System.out.println(v != null ? v : "Vehículo no encontrado.");
    }

    private void borrarVehiculo() {
        Consola.mostrarCabecera("Borrar Vehículo");
        try {
            String matricula = Consola.leerCadena("Matrícula: ");
            controlador.borrar(Vehiculo.getVehiculoValido(matricula));
            System.out.println("Vehículo eliminado.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private void insertarRevision() {
        Consola.mostrarCabecera("Nueva Revisión");
        try {
            String dni = Consola.leerCadena("DNI Cliente: ");
            String mat = Consola.leerCadena("Matrícula Vehículo: ");
            LocalDate fecha = Consola.leerFecha("Fecha inicio");

            Cliente c = Cliente.getClienteValido(dni);
            Vehiculo v = Vehiculo.getVehiculoValido(mat);
            controlador.insertar(new Revision(c, v, fecha));
            System.out.println("Revisión iniciada.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void cerrarRevision() {
        Consola.mostrarCabecera("Cerrar Revisión");
        try {
            String dni = Consola.leerCadena("DNI Cliente: ");
            String mat = Consola.leerCadena("Matrícula: ");
            LocalDate inicio = Consola.leerFecha("Fecha inicio exacta");

            Revision ref = new Revision(Cliente.getClienteValido(dni), Vehiculo.getVehiculoValido(mat), inicio);
            LocalDate fin = Consola.leerFecha("Fecha fin");
            controlador.cerrar(ref, fin);
            System.out.println("Revisión cerrada satisfactoriamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarTodo() {
        Consola.mostrarCabecera("Listado General");
        System.out.println("CLIENTES:");
        controlador.getClientes().forEach(System.out::println);
        System.out.println("\nVEHÍCULOS:");
        controlador.getVehiculos().forEach(System.out::println);
        System.out.println("\nREVISIONES:");
        controlador.getRevisiones().forEach(System.out::println);
    }
}

