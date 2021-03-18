package co.com.sofka.usecase.juego;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.juego.command.CrearJuego;
import co.com.sofka.domain.juego.events.JuegoCreado;
import co.com.sofka.domain.juego.events.JugadorAdicionado;
import co.com.sofka.domain.juego.values.Capital;
import co.com.sofka.domain.juego.values.JugadorId;
import co.com.sofka.domain.juego.values.Nombre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class CrearJuegoUseCaseTest {

    @Test
    void crearJuego() {
        var nombres = Map.of(
                JugadorId.of("xxxx"), new Nombre("Estefania"),
                JugadorId.of("ffff"), new Nombre("Luis"),
                JugadorId.of("cccc"), new Nombre("Carlos"),
                JugadorId.of("dddd"), new Nombre("Mario")
        );
        var capiltales = Map.of(
                JugadorId.of("xxxx"), new Capital(400),
                JugadorId.of("ffff"), new Capital(500),
                JugadorId.of("cccc"), new Capital(400),
                JugadorId.of("dddd"), new Capital(500)
        );
        var command = new CrearJuego(capiltales, nombres);
        var useCase = new CrearJuegoUseCase();

        var events = UseCaseHandler.getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var juegoCreado = (JuegoCreado) events.get(0);
        var jugadorAdicionadoParaEstefania = (JugadorAdicionado) events.get(4);
        var jugadorAdicionadoParaLuis = (JugadorAdicionado) events.get(3);
        var jugadorAdicionadoParaCarlos = (JugadorAdicionado) events.get(2);
        var jugadorAdicionadoParaMario = (JugadorAdicionado) events.get(1);

        Assertions.assertTrue(Objects.nonNull(juegoCreado.getJuegoId().value()));

        Assertions.assertEquals("Estefania", jugadorAdicionadoParaEstefania.getNombre().value());
        Assertions.assertEquals(400, jugadorAdicionadoParaEstefania.getCapital().value());
        Assertions.assertEquals("xxxx", jugadorAdicionadoParaEstefania.getJugadorId().value());

        Assertions.assertEquals("Luis", jugadorAdicionadoParaLuis.getNombre().value());
        Assertions.assertEquals(500, jugadorAdicionadoParaLuis.getCapital().value());
        Assertions.assertEquals("ffff", jugadorAdicionadoParaLuis.getJugadorId().value());

        Assertions.assertEquals("Carlos", jugadorAdicionadoParaCarlos.getNombre().value());
        Assertions.assertEquals(400, jugadorAdicionadoParaCarlos.getCapital().value());
        Assertions.assertEquals("cccc", jugadorAdicionadoParaCarlos.getJugadorId().value());

        Assertions.assertEquals("Mario", jugadorAdicionadoParaMario.getNombre().value());
        Assertions.assertEquals(500, jugadorAdicionadoParaMario.getCapital().value());
        Assertions.assertEquals("dddd", jugadorAdicionadoParaMario.getJugadorId().value());
    }

    @Test
    void errorAlCrearJuego() {
        var nombres = Map.of(
                JugadorId.of("xxxx"), new Nombre("Estefania")
        );
        var capiltales = Map.of(
                JugadorId.of("xxxx"), new Capital(400)
        );
        var command = new CrearJuego(capiltales, nombres);
        var useCase = new CrearJuegoUseCase();


        Assertions.assertThrows(BusinessException.class, () -> {
            UseCaseHandler.getInstance()
                    .syncExecutor(useCase, new RequestCommand<>(command))
                    .orElseThrow();
        }, "No se puede crear el juego por que no tiene la cantidad necesaria de jugadores");

    }
}