package co.com.sofka.usecase.juego;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.juego.command.AdicionarJugador;
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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdicionarJugadorUseCaseTest {
    @Mock
    private DomainEventRepository repository;

    @Test
    void adicionarJugador() {
        var id = JuegoId.of("xxxx");
        var jugadorId = JugadorId.of("dddd");
        var nombre = new Nombre ("Andrea");
        var capital = new Capital(400);
        var command = new AdicionarJugador(id, jugadorId, nombre, capital);
        var useCase = new AdicionarJugadorUseCase();

        when(repository.getEventsBy(id.value())).thenReturn(eventStored(id));
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(id.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();
        var jugadorAdicionado = (JugadorAdicionado) events.get(0);

        Assertions.assertEquals("Andrea", jugadorAdicionado.getNombre().value());
        Assertions.assertEquals(400, jugadorAdicionado.getCapital().value());
        Assertions.assertEquals("dddd", jugadorAdicionado.getJugadorId().value());

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