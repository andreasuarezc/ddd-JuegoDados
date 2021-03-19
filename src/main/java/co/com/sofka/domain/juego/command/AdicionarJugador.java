package co.com.sofka.domain.juego.command;

import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.juego.values.Capital;
import co.com.sofka.domain.juego.values.JuegoId;
import co.com.sofka.domain.juego.values.JugadorId;
import co.com.sofka.domain.juego.values.Nombre;

public class AdicionarJugador implements Command {
    private final JuegoId juegoId;
    private final JugadorId jugadorId;
    private final Nombre nombre;
    private final Capital capital;


    public AdicionarJugador(JuegoId juegoId, JugadorId jugadorId, Nombre nombre, Capital capital) {
        this.juegoId = juegoId;
        this.jugadorId = jugadorId;
        this.nombre = nombre;
        this.capital = capital;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Capital getCapital() {
        return capital;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }
}
