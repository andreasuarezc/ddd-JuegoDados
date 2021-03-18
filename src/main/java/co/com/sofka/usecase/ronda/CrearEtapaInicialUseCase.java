package co.com.sofka.usecase.ronda;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.ronda.Ronda;
import co.com.sofka.domain.ronda.events.DadosLanzados;
import co.com.sofka.domain.ronda.values.RondaId;

public class CrearEtapaInicialUseCase extends UseCase<TriggeredEvent<DadosLanzados>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<DadosLanzados> input) {
        var event = input.getDomainEvent();
        var ronda = Ronda.from(RondaId.of(event.aggregateRootId()), retrieveEvents());

        ronda.crearEtapaInicial();

        emit().onResponse(new ResponseEvents(ronda.getUncommittedChanges()));
    }
}