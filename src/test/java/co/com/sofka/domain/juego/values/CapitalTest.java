package co.com.sofka.domain.juego.values;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CapitalTest {
    @Test
    void capital(){
        var capital1 = new Capital(400);
        var capital2= new Capital(500);
        assertFalse(capital1.equals(capital2));
    }

    @Test
    void incrementarCapital() {
        var capital1 = new Capital(400);
        var capital2 = capital1.incrementarCapital(20);
        assertTrue(capital2.value().equals(420));
    }

    @Test
    void disminuirCapital() {
        var capital1 = new Capital(400);
        var capital2 = capital1.disminuirCapital(20);
        assertTrue(capital2.value().equals(380));
    }
}