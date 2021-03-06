package co.com.sofka.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.juego.values.JuegoId;

public class JuegoCreado extends DomainEvent {
    private final JuegoId juegoId;

    public JuegoCreado(JuegoId juegoId) {
        super("nomemientas.juego.juegocreado");
        this.juegoId = juegoId;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }
}
