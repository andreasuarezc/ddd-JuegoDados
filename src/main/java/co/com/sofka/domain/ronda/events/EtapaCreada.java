package co.com.sofka.domain.ronda.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.juego.values.JuegoId;
import co.com.sofka.domain.ronda.values.Cara;
import co.com.sofka.domain.ronda.values.EtapaId;

import java.util.List;

public class EtapaCreada extends DomainEvent {
    private final JuegoId juegoId;
    private final EtapaId etapaId;
    private final List<Cara> carasVisibles;

    public EtapaCreada(JuegoId juegoId, EtapaId etapaId, List<Cara> carasVisibles) {
        super("nomemientan.ronda.etapacreada");
        this.juegoId = juegoId;
        this.etapaId = etapaId;
        this.carasVisibles = carasVisibles;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }

    public EtapaId getEtapaId() {
        return etapaId;
    }

    public List<Cara> getCarasVisibles() {
        return carasVisibles;
    }
}