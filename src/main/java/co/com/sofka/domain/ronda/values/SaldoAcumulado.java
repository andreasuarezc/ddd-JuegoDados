package co.com.sofka.domain.ronda.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class SaldoAcumulado implements ValueObject<Integer> {
    private final Integer value;

    public SaldoAcumulado(Integer value) {
        this.value = Objects.requireNonNull(value);
        if(value<0){
            throw new IllegalArgumentException("No se permite acumulado negativo");
        }
    }

    public SaldoAcumulado aumentarSaldoAcumulado(Integer value){
        return new SaldoAcumulado(this.value + value);
    }
    @Override
    public Integer value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaldoAcumulado that = (SaldoAcumulado) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
