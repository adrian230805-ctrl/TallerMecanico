package org.iesalandalus.programacion.tallermecanico.modelo;

public class TallerMecanicoExcepcion extends Exception {
    private String miMensajePersonalizado;

    public TallerMecanicoExcepcion(String mensaje) {
        this.miMensajePersonalizado = mensaje;
    }

    @Override
    public String getMessage() {
        return this.miMensajePersonalizado;
    }
}
