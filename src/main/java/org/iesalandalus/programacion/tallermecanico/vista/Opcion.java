package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.HashMap;
import java.util.Map;

public enum Opcion {
    INSERTAR_CLIENTE("Insertar Cliente", 1),
    BUSCAR_CLIENTE("Buscar Cliente", 2),
    BORRAR_CLIENTE("Borrar Cliente", 3),
    INSERTAR_VEHICULO("Insertar Vehículo", 4),
    BUSCAR_VEHICULO("Buscar Vehículo", 5),
    BORRAR_VEHICULO("Borrar Vehículo", 6),
    INSERTAR_REVISION("Insertar Revisión", 7),
    CERRAR_REVISION("Cerrar Revisión", 8),
    LISTAR_TODO("Listar todo el modelo", 9),
    SALIR("Salir", 0);

    private final String texto;
    private final int numero;

    private static final Map<Integer, Opcion> OpcionesMap = new HashMap<>();

    static {
        for (Opcion opcion : Opcion.values()) {
            OpcionesMap.put(opcion.getNumero(), opcion);
        }
    }

    private Opcion(String texto, int numero) {
        this.texto = texto;
        this.numero = numero;
    }

    public String getTexto() { return texto; }
    public int getNumero() { return numero; }

    public static boolean esValida(int numero) {
        return OpcionesMap.containsKey(numero);
    }

    public static Opcion get(int numero) {
        if (!esValida(numero)) {
            throw new IllegalArgumentException("Opción de menú no válida.");
        }
        return OpcionesMap.get(numero);
    }

    @Override
    public String toString() {
        return String.format("%d. %s", numero, texto);
    }
}
