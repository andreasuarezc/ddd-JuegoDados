package co.com.sofka.domain.juego.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Capital implements ValueObject<Integer> {
    private final Integer value;

    public Capital(Integer value) {
        this.value = Objects.requireNonNull(value);
        if(value<=0){
            throw new IllegalArgumentException("El capital no puede ser cero");
        }
    }
    //Estas son reglas de negocio
    public Capital incrementarCapital(Integer value){
        return new Capital(this.value + value);
    }

    public Capital disminuirCapital(Integer value){
        return new Capital(this.value - value);
    }

    @Override
    public Integer value() {
        return value;
    }
}
