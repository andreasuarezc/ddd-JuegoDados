package co.com.sofka.domain.ronda;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.juego.values.JuegoId;
import co.com.sofka.domain.juego.values.JugadorId;
import co.com.sofka.domain.ronda.events.DadosLanzados;
import co.com.sofka.domain.ronda.events.EtapaCreada;
import co.com.sofka.domain.ronda.events.RondaCreada;
import co.com.sofka.domain.ronda.events.RondaInicializada;
import co.com.sofka.domain.ronda.values.Cara;
import co.com.sofka.domain.ronda.values.DadoId;
import co.com.sofka.domain.ronda.values.EtapaId;
import co.com.sofka.domain.ronda.values.RondaId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Ronda extends AggregateEvent<RondaId> {
    protected JuegoId juegoId;
    protected Map<DadoId, Dado> dados;
    protected Map<EtapaId, Etapa> etapas;
    protected Map<JuegoId, Punto> puntaje;
    protected Set<JugadorId> jugadorIds;

    public Ronda(RondaId entityId, JuegoId juegoId, Set<JugadorId> jugadorIds) {
        super(entityId);
        appendChange(new RondaCreada(jugadorIds, juegoId)).apply();
    }

    private Ronda(RondaId entityId) {
        super(entityId);
        subscribe(new RondaChange(this));
    }

    public static Ronda from(RondaId entityId, List<DomainEvent> events) {
        var ronda = new Ronda(entityId);
        events.forEach(ronda::applyEvent);
        return ronda;
    }

    public void inicializarRonda() {
        appendChange(new RondaInicializada(juegoId, jugadorIds)).apply();
    }


    public void tirarDados() {
        var carasList = this.dados
                .values()
                .stream()
                .map(dado -> Map.of(dado.identity(), dado.caras()))
                .collect(Collectors.toList());
        appendChange(new DadosLanzados(juegoId, carasList)).apply();
    }

    public void crearEtapaInicial() {
        List<Cara> carasVisibles = new ArrayList<>();
        appendChange(new EtapaCreada(juegoId, EtapaId.of(1), carasVisibles)).apply();
    }
}
