package co.com.sofka.domain.ronda.values;

import co.com.sofka.domain.generic.Identity;

public class DadoId extends Identity {
    private DadoId(Integer num) {
        super(num.toString());
    }

    public static DadoId of(Integer num) {
        return new DadoId(num);
    }
}
