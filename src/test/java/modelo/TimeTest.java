package modelo;

import campfut.model.Jogador;
import campfut.model.Time;
import campfut.services.TimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {
    private TimeService timeService;
    private Time time;

    @BeforeEach
    public void setUp() {
        timeService = new TimeService();
        time = new Time();
        time.setId(1);
        time.setNome("Time A");
        time.setJogadores(new ArrayList<>());
        time.setPartidasComoMandante(new ArrayList<>());
        time.setPartidasComoVisitante(new ArrayList<>());
    }

    @Test
    public void deveCalcularAlturaMediaDosJogadoresCorretamente() {
        TimeService timeService = new TimeService();
        ArrayList<Jogador> jogadores = new ArrayList<>();
        jogadores.add(new Jogador(1, null, "João", null, 1.80f, null));
        jogadores.add(new Jogador(2, null, "Pedro", null, 1.70f, null));
        jogadores.add(new Jogador(3, null, "Lucas", null, 1.90f, null));

        Time time = new Time(1, "Vasco", jogadores, null, new ArrayList<>(), new ArrayList<>(), null);

        double mediaEsperada = (1.80 + 1.70 + 1.90) / 3;
        assertEquals(mediaEsperada, timeService.getAlturaMedia(time), 0.001);
    }

    @Test
    public void deveAdicionarERemoverJogador() {
        Jogador jogador1 = new Jogador(1, LocalDate.now(), "Jogador 1", "M", 1.8f, null);
        Jogador jogador2 = new Jogador(2, LocalDate.now(), "Jogador 2", "M", 1.75f, null);

        timeService.adicionarJogador(time, jogador1);
        timeService.adicionarJogador(time, jogador2);

        assertEquals(2, time.getJogadores().size());
        assertTrue(time.getJogadores().contains(jogador1));
        assertTrue(time.getJogadores().contains(jogador2));

        timeService.removerJogador(time, jogador1);

        assertEquals(1, time.getJogadores().size());
        assertFalse(time.getJogadores().contains(jogador1));
        assertTrue(time.getJogadores().contains(jogador2));
    }

    @Test
    public void naoDeveAdicionarJogadorJaEmOutroTime() {
        Time outroTime = new Time(2, "Time B", new ArrayList<>(), null, new ArrayList<>(), new ArrayList<>(), null);
        Jogador jogador = new Jogador(1, LocalDate.now(), "Jogador", "M", 1.8f, null);

        timeService.adicionarJogador(time, jogador);
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> timeService.adicionarJogador(outroTime, jogador),
                "Jogador já pertence a outro time");
        assertEquals("Jogador já pertence a outro time", exception.getMessage());
    }
}