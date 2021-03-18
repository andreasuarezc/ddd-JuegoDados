package co.com.sofka.domain.juego.command;

import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.juego.values.JuegoId;

public class InicializarJuego implements Command {

    private final JuegoId juegoId;

    public InicializarJuego(JuegoId juegoId) {
        this.juegoId = juegoId;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }
}