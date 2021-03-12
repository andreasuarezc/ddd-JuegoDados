package co.com.sofka.domain.juego.values;

import co.com.sofka.domain.generic.Identity;

public class JugadorId extends Identity {

    public JugadorId(String uid) {
        super(uid);
    }

    public static JugadorId of(String uid) {
        return new JugadorId(uid);
    }

}
