package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.regex.Pattern;
import java.util.Objects;

public record Vehiculo(String marca, String modelo, String matricula) {

    private static final String ER_MARCA = "^(Seat|Land Rover|KIA|Rolls-Royce|SsangYong)$";
    private static final String ER_MATRICULA = "^\\d{4}[B-DF-HJ-NP-TV-Z]{3}$";

    public Vehiculo {
        validarMarca(marca);
        validarModelo(modelo);
        validarMatricula(matricula);
    }

    private static void validarMarca(String marca) {
        if (marca == null || !Pattern.matches(ER_MARCA, marca)) {
            throw new IllegalArgumentException("Marca no válida. Debe ser: Seat, Land Rover, KIA, Rolls-Royce o SsangYong.");
        }
    }

    private static void validarModelo(String modelo) {
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede estar en blanco.");
        }
    }

    private static void validarMatricula(String matricula) {
        if (matricula == null || !Pattern.matches(ER_MATRICULA, matricula.toUpperCase())) {
            throw new IllegalArgumentException("Formato de matrícula no válido (1111BBB).");
        }
    }

    public static Vehiculo getVehiculoValido(String matricula) {
        return new Vehiculo("KIA", "Generico", matricula);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(matricula.toUpperCase(), vehiculo.matricula.toUpperCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula.toUpperCase());
    }

    @Override
    public String toString() {
        return String.format("Vehiculo [marca=%s, modelo=%s, matricula=%s]",
                marca, modelo, matricula);
    }
}

