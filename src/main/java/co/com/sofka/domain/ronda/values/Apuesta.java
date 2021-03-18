package co.com.sofka.domain.ronda.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Apuesta implements ValueObject<Apuesta.Valor> {
    private final Adivinanza adivinanza;
    private final dineroApuesta dineroApuesta;

    public Apuesta(Adivinanza adivinanza, dineroApuesta dineroApuesta) {
        this.adivinanza = Objects.requireNonNull(adivinanza);
        this.dineroApuesta = Objects.requireNonNull(dineroApuesta);
    }

    @Override
    public Valor value() {
        return new Valor() {
            @Override
            public Adivinanza adivinanza() {
                return adivinanza;
            }

            @Override
            public dineroApuesta apuesta() {
                return dineroApuesta;
            }
        };
    }

    public interface Valor {
        Adivinanza adivinanza();

        dineroApuesta apuesta();
    }
}