package co.com.sofka.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.juego.values.JugadorId;
import java.util.Set;

public class JuegoInicializado extends DomainEvent {
    private final Set<JugadorId> jugadoresIds;

    public JuegoInicializado(Set<JugadorId> jugadoresIds) {
        super("nomemientas.juego.juegoinicializado");
        this.jugadoresIds = jugadoresIds;
    }

    public Set<JugadorId> getJugadoresIds() {
        return jugadoresIds;
    }
}