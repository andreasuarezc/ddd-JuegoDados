package co.com.sofka.domain.juego.values;

import co.com.sofka.domain.generic.Identity;

public class JuegoId extends Identity {
    public JuegoId(String uid) {
        super(uid);
    }

    public JuegoId() {
    }

    public static JuegoId of(String uid) {
        return new JuegoId(uid);
    }
}
