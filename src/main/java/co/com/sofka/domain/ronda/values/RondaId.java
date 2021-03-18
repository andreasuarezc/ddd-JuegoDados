package co.com.sofka.domain.ronda.values;

import co.com.sofka.domain.generic.Identity;

public class RondaId extends Identity {
    public RondaId(String uid) {
        super(uid);
    }
    public RondaId(){
    }
    public static RondaId of (String uuid){return new RondaId(uuid);}
}
