package co.com.sofka.domain.juego;

import co.com.sofka.domain.generic.EventChange;
import co.com.sofka.domain.juego.events.JuegoCreado;
import co.com.sofka.domain.juego.events.JuegoInicializado;
import co.com.sofka.domain.juego.events.JugadorAdicionado;
import java.util.HashMap;

public class JuegoChange extends EventChange {
    public JuegoChange(Juego juego) {
        //Estas son reglas de dominio
        apply((JuegoCreado event) ->{
            juego.isIniciado = Boolean.FALSE;
            juego.jugadores = new HashMap<>();
        });
        apply((JuegoInicializado event) -> {
            juego.isIniciado = Boolean.TRUE;
        });

        apply((JugadorAdicionado event) ->{
            if (juego.isIniciado.equals(Boolean.TRUE)){
                throw new IllegalArgumentException("No se puede crear un nuevo jugador si el juego esta en march");
            }
            juego.jugadores.put(event.getJugadorId(),
                    new Jugador(
                           event.getJugadorId(),
                           event.getNombre(),
                           event.getCapital()
                    )
            );
        });
    }
}
