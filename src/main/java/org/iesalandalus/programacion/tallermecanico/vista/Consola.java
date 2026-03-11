package org.iesalandalus.programacion.tallermecanico.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Consola {

    private static final Scanner entrada = new Scanner(System.in);
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Consola() {}

    public static void mostrarCabecera(String mensaje) {
        System.out.println(mensaje);
        System.out.println("-".repeat(mensaje.length()));
    }

    public static void mostrarMenu() {
        mostrarCabecera("Gestión de Revisiones de Vehículos");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return entrada.nextLine();
    }

    public static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduce un número entero válido.");
            }
        }
    }

    public static double leerReal(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduce un número real válido.");
            }
        }
    }

    public static LocalDate leerFecha(String mensaje) {
        while (true) {
            String fechaStr = leerCadena(mensaje + " (dd/MM/yyyy): ");
            try {
                return LocalDate.parse(fechaStr, FORMATO_FECHA);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato de fecha incorrecto. Inténtalo de nuevo.");
            }
        }
    }

    public static Opcion elegirOpcion() {
        while (true) {
            int num = leerEntero("\nIntroduce una opción: ");
            if (Opcion.esValida(num)) {
                return Opcion.get(num);
            }
            System.out.println("Opción no válida. Por favor, selecciona una de la lista.");
        }
    }


    public static void mensajeError(String mensaje) {
        System.out.println("❌ ERROR: " + mensaje);
    }

    public static void mensajeExito(String mensaje) {
        System.out.println("✅ ÉXITO: " + mensaje);
    }

    public static void limpiarPantalla() {
        for (int i = 0; i < 20; i++) System.out.println();
    }
}

