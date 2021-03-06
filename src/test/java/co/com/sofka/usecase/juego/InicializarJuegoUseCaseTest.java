package co.com.sofka.usecase.juego;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.juego.command.InicializarJuego;
import co.com.sofka.domain.juego.events.JuegoCreado;
import co.com.sofka.domain.juego.events.JuegoInicializado;
import co.com.sofka.domain.juego.events.JugadorAdicionado;
import co.com.sofka.domain.juego.values.Capital;
import co.com.sofka.domain.juego.values.JuegoId;
import co.com.sofka.domain.juego.values.JugadorId;
import co.com.sofka.domain.juego.values.Nombre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class InicializarJuegoUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void inicializarJuego() {
        var id = JuegoId.of("xxxx");
        var command = new InicializarJuego(id);
        var useCase = new InicializarJuegoUseCase();

        when(repository.getEventsBy(id.value())).thenReturn(eventStored(id));
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(id.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var juegoInicializado = (JuegoInicializado) events.get(0);
        Assertions.assertEquals(3, juegoInicializado.getJugadoresIds().size());
    }

    private List<DomainEvent> eventStored(JuegoId id) {
        return List.of(
                new JuegoCreado(id),
                new JugadorAdicionado(JugadorId.of("xxxx"), new Nombre("Estefania"), new Capital(500)),
                new JugadorAdicionado(JugadorId.of("cccc"), new Nombre("Luis"), new Capital(400)),
                new JugadorAdicionado(JugadorId.of("dddd"), new Nombre("Mario"), new Capital(500))
        );
    }
}