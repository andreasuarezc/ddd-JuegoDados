package co.com.sofka.domain.juego;

import co.com.sofka.domain.generic.Entity;
import co.com.sofka.domain.juego.values.Capital;
import co.com.sofka.domain.juego.values.JugadorId;
import co.com.sofka.domain.juego.values.Nombre;

public class Jugador extends Entity<JugadorId> {
    private final Nombre nombre;
    private Capital capital;

    public Jugador(JugadorId entityId, Nombre nombre, Capital capital) {
        super(entityId);
        this.nombre = nombre;
        this.capital = capital;
    }

    public void aumentarCapital(Integer valor) {
        this.capital = this.capital.incrementarCapital(valor);
    }

    public Capital capital() {
        return capital;
    }

    public Nombre nombre() {
        return nombre;
    }
}
