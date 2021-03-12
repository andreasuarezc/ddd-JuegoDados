package co.com.sofka.domain.juego;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.juego.events.JuegoCreado;
import co.com.sofka.domain.juego.events.RondaIniciada;
import co.com.sofka.domain.juego.values.JuegoId;
import co.com.sofka.domain.juego.values.JugadorId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JuegoTest {

    @Test
    void iniciarRonda() {
        var juegoId = JuegoId.of("xxx-xxx-xxx");
        var juego = Juego.from(juegoId, eventList());
        juego.iniciarRonda();
        var rondaIniciada = (RondaIniciada)juego.getUncommittedChanges().get(0);
        Assertions.assertEquals(juegoId.value(), rondaIniciada.aggregateRootId());
    }

    private List<DomainEvent> eventList() {
        var jugadores = Map.of(
                JugadorId.of("xxxx-xxx1"), new Jugador(JugadorId.of("xxxx-xxx1")),
                JugadorId.of("xxxx-xxx2"), new Jugador(JugadorId.of("xxxx-xxx2")),
                JugadorId.of("xxxx-xxx3"), new Jugador(JugadorId.of("xxxx-xxx3"))
        );
        return List.of(
                new JuegoCreado(jugadores),
                new RondaIniciada()
                );
    }
}