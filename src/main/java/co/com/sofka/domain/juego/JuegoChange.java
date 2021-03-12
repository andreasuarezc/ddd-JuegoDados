package co.com.sofka.domain.juego;

import co.com.sofka.domain.generic.EventChange;
import co.com.sofka.domain.juego.events.JuegoCreado;
import co.com.sofka.domain.juego.events.RondaIniciada;

public class JuegoChange extends EventChange {

    public JuegoChange(Juego juego) {
        //Estas son reglas de dominio
        apply((JuegoCreado event) ->{
            if(event.getJugadores().size()<3){
                throw  new IllegalArgumentException("No se Puede crear el juego " +
                        "porque no se tiene la cantidad de Jugadores");
            }
            juego.jugadores = event.getJugadores();
            juego.isIniciado = Boolean.FALSE;
        });
        apply((RondaIniciada event) -> {
            if(Boolean.TRUE.equals(juego.isIniciado)){
                new IllegalArgumentException("No puede inicializar la Ronda");
            }
            juego.isIniciado = Boolean.TRUE;
        });
    }
}
