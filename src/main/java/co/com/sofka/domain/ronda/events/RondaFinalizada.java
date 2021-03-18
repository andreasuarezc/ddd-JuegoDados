package co.com.sofka.domain.ronda.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.juego.values.JuegoId;

public class RondaFinalizada extends DomainEvent {
    private final JuegoId juegoId;

    public RondaFinalizada(JuegoId juegoId) {
        super("nomemientan.ronda.rondafinalizada");
        this.juegoId = juegoId;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }
}