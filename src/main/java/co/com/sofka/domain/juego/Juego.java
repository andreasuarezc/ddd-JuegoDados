package co.com.sofka.domain.juego;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.juego.events.JuegoInicializado;
import co.com.sofka.domain.juego.events.JugadorAdicionado;
import co.com.sofka.domain.juego.events.JuegoCreado;
import co.com.sofka.domain.juego.factory.JugadorFactory;
import co.com.sofka.domain.juego.values.Capital;
import co.com.sofka.domain.juego.values.JuegoId;
import co.com.sofka.domain.juego.values.JugadorId;
import co.com.sofka.domain.juego.values.Nombre;
import co.com.sofka.domain.ronda.values.RondaId;
import java.util.List;
import java.util.Map;

public class Juego extends AggregateEvent<JuegoId> {
    protected Map<JugadorId, Jugador> jugadores;
    protected Boolean isIniciado;
    protected RondaId rondaId;


    public Juego(JuegoId entityId, JugadorFactory jugadorFactory) {
        super(entityId);
        appendChange(new JuegoCreado(entityId)).apply();
        jugadorFactory.jugadores().forEach(jugador -> adicionarJugador(jugador.identity(), jugador.nombre(),  jugador.capital()));
    }
    public Juego(JuegoId entityId){
        super(entityId);
        subscribe(new JuegoChange(this));
    }

    //public void iniciarRonda(){appendChange(new RondaIniciada()).apply();}

    public static Juego from (JuegoId entityId, List<DomainEvent> events){
        var juego = new Juego(entityId);
        events.forEach(juego:: applyEvent);
        return juego;
    }

    public void adicionarJugador(JugadorId jugadorId, Nombre nombre, Capital capital) {
        appendChange(new JugadorAdicionado(jugadorId, nombre, capital)).apply();
    }

    public void iniciarJuego() {
        var jugadoresIds = jugadores.keySet();
        appendChange(new JuegoInicializado(jugadoresIds)).apply();
    }

    public RondaId rondaId() {
        return rondaId;
    }

    public Boolean IsIniciado() {
        return isIniciado;
    }
}
