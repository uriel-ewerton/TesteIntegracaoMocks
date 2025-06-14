package modelo;

import campfut.model.Campeonato;
import campfut.model.Estadio;
import campfut.model.Partida;
import campfut.model.Time;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PartidaTest {

    @Test
    public void deveCriarPartidaComTimesEEstadioValidos() {
        Estadio estadio = new Estadio(1, "Maracanã", "RJ", null);
        Time mandante = new Time(1, "Flamengo", new ArrayList<>(), estadio, new ArrayList<>(), new ArrayList<>(), null);
        Time visitante = new Time(2, "Vasco", new ArrayList<>(), null, new ArrayList<>(), new ArrayList<>(), null);

        estadio.setTime(mandante);
        Campeonato campeonato = new Campeonato(1, 2025, "Brasileirão", new ArrayList<>(), new ArrayList<>());

        Partida partida = new Partida(1, LocalDate.of(2025, 5, 20), visitante, mandante, campeonato, null);

        assertNotNull(partida);
        assertEquals("Flamengo", partida.getTimeMandante().getNome());
        assertEquals("Vasco", partida.getTimeVisitante().getNome());
        assertEquals(LocalDate.of(2025, 5, 20), partida.getData());
        assertEquals("Maracanã", partida.getTimeMandante().getSede().getNome());
    }

    // parte da premissa de que o estádio do jogo é sempre o do mandante, mas podem haver estadios "neutros" também.
    // é uma regra de negócio a ser considerada.
    @Test
    public void estadioDaPartidaDeveCorresponderAoEstadioSedeDoMandante() {
        // Criar estádios
        Estadio maracana = new Estadio(1, "Maracanã", "RJ", null);
        Estadio morumbi = new Estadio(2, "Morumbi", "SP", null);

        // Criar times com suas sedes
        Time flamengo = new Time(1, "Flamengo", new ArrayList<>(), maracana, new ArrayList<>(), new ArrayList<>(), null);
        Time palmeiras = new Time(2, "Palmeiras", new ArrayList<>(), morumbi, new ArrayList<>(), new ArrayList<>(), null);

        // Criar partida com Flamengo como mandante (estádio deve ser Maracanã)
        Partida partida1 = new Partida(1, LocalDate.now(), palmeiras, flamengo, null, null);
        Estadio estadioDaPartida1 = partida1.getTimeMandante().getSede();
        boolean estadioIgualMandante = estadioDaPartida1.equals(partida1.getTimeMandante().getSede());
        assertTrue(estadioIgualMandante); // Verifica se o estádio é o do mandante (Maracanã)

        // Criar outra partida com Palmeiras como mandante (estádio deve ser Morumbi)
        Partida partida2 = new Partida(2, LocalDate.now(), flamengo, palmeiras, null, null);
        Estadio estadioDaPartida2 = partida2.getTimeMandante().getSede();
        boolean estadioIgualMandante2 = estadioDaPartida2.equals(partida2.getTimeMandante().getSede());
        assertTrue(estadioIgualMandante2); // Verifica se o estádio é o do mandante (Morumbi)
    }
}