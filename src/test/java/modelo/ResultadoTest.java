package modelo;

import campfut.model.Resultado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ResultadoTest {

    @Test
    public void testResultadoInvalidoMandante() {
        assertThrows(IllegalArgumentException.class, () -> new Resultado(-1, 2));
    }

    @Test
    public void testResultadoInvalidoVisitante() {
        assertThrows(IllegalArgumentException.class, () -> new Resultado(2, -1));
    }

    @Test
    public void testResultadoInvalidoAmbos() {
        assertThrows(IllegalArgumentException.class, () -> new Resultado(-1, -1));
    }

    @Test
    public void testResultadoValido() {
        assertDoesNotThrow(() -> new Resultado(0, 0));
        assertDoesNotThrow(() -> new Resultado(1, 2));
        assertDoesNotThrow(() -> new Resultado(3, 1));
    }
}