package co.com.sofka.domain.ronda.values;

import co.com.sofka.domain.generic.ValueObject;
import java.util.Objects;

public class dineroApuesta implements ValueObject<Integer> {
    private final Integer value;

    public dineroApuesta(Integer value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public Integer value() {
        return value;
    }
}