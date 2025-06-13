package modelo;

import campfut.model.Jogador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class JogadorTest {
    private Jogador jogador;

    @BeforeEach
    public void setUp() {
        jogador = new Jogador();
        jogador.setId(1);
        jogador.setNome("Pedro Ariel");
        jogador.setDataNascimento(LocalDate.of(1990, 1, 15));
        jogador.setGenero("Masculino");
        jogador.setAltura(1.77f);
    }

    @Test
    void deveCalcularIdadeJogadorPelaDataNascimento() {
        // Assumindo ano atual como 2025 para consistência
        assertEquals(35, jogador.getIdade());
    }

    @Test
    void naoDeveTerDataDeNascimentoFutura() {
        LocalDate amanha = LocalDate.now().plusDays(1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> jogador.setDataNascimento(amanha),
                "A data não deve ser depois da data atual");
        assertEquals("A data não deve ser depois da data atual.", exception.getMessage());
    }

    @Test
    void naoDeveTerIdadeAvancadaImpossivel() {
        LocalDate muitoAntiga = LocalDate.now().minusYears(120).minusDays(1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> jogador.setDataNascimento(muitoAntiga),
                "A data não deve ser antiga de forma impossível");
        assertEquals("A data não deve ser antiga de forma impossivel.", exception.getMessage());
    }

    @Test
    void naoDeveTerAlturaNegativa() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> jogador.setAltura(-0.01f),
                "A altura deve ser maior que zero");
        assertEquals("Altura deve ser maior que zero", exception.getMessage());
    }

    @Test
    void naoDeveTerAlturaZero() { // Corrigindo o teste original que estava confuso
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> jogador.setAltura(0f),
                "A altura deve ser maior que zero");
        assertEquals("Altura deve ser maior que zero", exception.getMessage());
    }
}