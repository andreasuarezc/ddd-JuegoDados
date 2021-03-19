package co.com.sofka.usecase.juego;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.juego.Juego;
import co.com.sofka.domain.juego.command.AdicionarJugador;

public class AdicionarJugadorUseCase extends UseCase<RequestCommand<AdicionarJugador>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AdicionarJugador> input) {
        var command = input.getCommand();
        var jugadorId = command.getJugadorId();
        var nombre = command.getNombre();
        var capital = command.getCapital();

        var juego = Juego.from(command.getJuegoId(), retrieveEvents());

        juego.adicionarJugador(jugadorId, nombre, capital);

        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));

    }

}
