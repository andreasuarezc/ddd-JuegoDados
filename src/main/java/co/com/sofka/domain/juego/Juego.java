package co.com.sofka.domain.juego;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.juego.events.JuegoCreado;
import co.com.sofka.domain.juego.values.JuegoId;
import co.com.sofka.domain.juego.values.JugadorId;

import java.util.List;
import java.util.Map;

//Cada agregado lanza eventos de dominio. Despues en la capa de Infraestructura, esos eventos de dominio los puedo traducir a una tabla.
/*
Aggregate Event es una clase abstracta que permite al agregado hacer uso de unos métodos abstractos, tener ciertos comportamientos.
 */
public class Juego extends AggregateEvent<JuegoId> {
    protected Map<JugadorId, Jugador> jugadores;

    /*un evento que pasa al principio del juego cuando lo creo,
    aplico un evento de dominio que se llama Juego Creado,
    el cual es creado con estos jugadores por ahora,

     */
    public Juego(JuegoId entityId, Map<JugadorId, Jugador> jugadores) {
        super(entityId);
        /*juegoCreado es el evento de dominio, se expresa en pasado, el evento ya pasó.
        El juego es creado con esa lista de jugadores.
        Cuando le digo al objeto nuevo juego, y le paso los jugadores,
        la consecuencia es juegoCreado, los atributos que tiene el juego creado son los jugadores y su agregado identificado.
        Para poder reconstruir ese agregado, minimamente debo tener un evento, esos eventos los acumulo en una base de datos. Ejemplo, ya tengo el juego creado,
        u otro comportameinto, entonces
        */
        appendChange(new JuegoCreado(jugadores)).apply();

    }
    //Este juego base lo creo como un privado
    private Juego(JuegoId entityId){
        super(entityId);
    }

    /*Temgo una lista de eventos, este método lo que me va a permitir es
    traerme el Juego a partir de unso eventos que ya pasaron.
    Es como reconstruir mi agregado a partir de ese evento.
    Me pasan un identificador, identifico el juego y despues le digo al juego que cré que aplique todos los eventos.
    Le estoy diciendo que reconstruya el agregado a traves de los eventos.
    Puedo decir en qué estado está el juego a partir de lo que ya pasó.
    Tengo un Set de eventos que los puedo guardar, los puedo persistir,
    */
    public static Juego from (JuegoId juegoId, List<DomainEvent> events){
        var juego = new Juego(juegoId);
        events.forEach(juego:: applyEvent);
        return juego;
    }
}
