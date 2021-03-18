package co.com.sofka.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.juego.values.Capital;
import co.com.sofka.domain.juego.values.JugadorId;
import co.com.sofka.domain.juego.values.Nombre;

public class JugadorAdicionado extends DomainEvent {
    private final JugadorId jugadorId;
    private final Nombre nombre;
    private final Capital capital;

    public JugadorAdicionado(JugadorId jugadorId, Nombre nombre, Capital capital) {
        super("nomemientan.juego.jugadoradicionado");
        this.jugadorId = jugadorId;
        this.nombre = nombre;
        this.capital = capital;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public Capital getCapital() {
        return capital;
    }

    public Nombre getNombre() {
        return nombre;
    }
}