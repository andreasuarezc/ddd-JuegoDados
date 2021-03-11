package co.com.sofka.domain.juego;

import co.com.sofka.domain.generic.Entity;
import co.com.sofka.domain.juego.values.JugadorId;

public class Jugador extends Entity<JugadorId> {

    public Jugador(JugadorId entityId) {
        super(entityId);
    }
}
