package co.com.sofka.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;

public class RondaIniciada extends DomainEvent {

    public RondaIniciada() {
        //Tengo que tipar todos los eventos de dominio para poder identificarlos
        super("nomemientas.rondainiciada");
    }
}
