package co.com.sofka.domain.ronda;

import co.com.sofka.domain.generic.Entity;
import co.com.sofka.domain.juego.values.JugadorId;
import co.com.sofka.domain.ronda.values.Cara;
import co.com.sofka.domain.ronda.values.Apuesta;
import co.com.sofka.domain.ronda.values.EtapaId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Etapa extends Entity<EtapaId> {
    private final List<Cara> carasVisibles;
    private final Map<JugadorId, Apuesta> apuestas;

    public Etapa(EtapaId entityId, List<Cara> carasVisibles) {
        super(entityId);
        this.carasVisibles = carasVisibles;
        this.apuestas = new HashMap<>();
    }

    public void agregarCaraVisible(Cara cara) {
        carasVisibles.add(cara);
    }

    public void aggregarCases(JugadorId jugadorId, Apuesta apuesta) {
        this.apuestas.put(jugadorId, apuesta);
    }

    public Map<JugadorId, Apuesta> apuestas() {
        return apuestas;
    }

    public List<Cara> carasVisibles() {
        return carasVisibles;
    }
}
