package modelo;

import campfut.model.*;
import campfut.services.CampeonatoService;
import campfut.services.TimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CampeonatoTest {
    private CampeonatoService campeonatoService;
    private TimeService timeService;
    private Campeonato campeonato;
    private Time timeA;
    private Time timeB;

    @BeforeEach
    public void setUp() {
        campeonatoService = new CampeonatoService();
        timeService = new TimeService();
        campeonato = new Campeonato();
        campeonato.setId(1);
        campeonato.setAno(LocalDate.now().getYear());
        campeonato.setNome("Campeonato Teste");
        campeonato.setTimes(new ArrayList<>());
        campeonato.setPartidas(new ArrayList<>());

        timeA = new Time(1, "Time A", new ArrayList<>(), null, new ArrayList<>(), new ArrayList<>(), campeonato);
        timeB = new Time(2, "Time B", new ArrayList<>(), null, new ArrayList<>(), new ArrayList<>(), campeonato);

        campeonatoService.adicionarTime(campeonato, timeA);
        campeonatoService.adicionarTime(campeonato, timeB);
    }

    @Test
    public void deveLancarExcecaoAoAdicionarTimeDuplicado() {
        CampeonatoService campeonatoService = new CampeonatoService();
        Campeonato campeonato = new Campeonato(1, 2025, "Copa", new ArrayList<>(), new ArrayList<>());
        Time time1 = new Time(1, "Flamengo", new ArrayList<>(), null, new ArrayList<>(), new ArrayList<>(), null);

        campeonatoService.adicionarTime(campeonato, time1);

        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            campeonatoService.adicionarTime(campeonato, time1);
        });

        assertEquals("O time já está no campeonato.", excecao.getMessage());
    }

    @Test
    public void deveFiltrarPorData() {
        CampeonatoService service = new CampeonatoService();
        LocalDate futureDate = LocalDate.of(2025, 5, 21);
        LocalDate anotherDate = LocalDate.of(2025, 5, 22);
        // Setup campeonato with partidas, ensuring no past dates
        Partida p1 = new Partida(1, futureDate, timeB, timeA, campeonato, null);
        Partida p2 = new Partida(2, futureDate, timeA, timeB, campeonato, null);
        Partida p3 = new Partida(3, anotherDate, timeA, timeB, campeonato, null);
        campeonato.setPartidas(new ArrayList<>(List.of(p1, p2, p3))); // Bypass service to set directly for test
        List<Partida> result = service.filtrarPorData(campeonato, futureDate);
        assertEquals(2, result.size());
        assertTrue(result.contains(p1));
        assertTrue(result.contains(p2));
    }

    @Test
    public void deveFiltrarPorEstadio() {
        CampeonatoService service = new CampeonatoService();
        Estadio maracana = new Estadio(1, "Maracanã", "RJ", null);
        Estadio morumbi = new Estadio(2, "Morumbi", "SP", null);
        Time flamengo = new Time(1, "Flamengo", new ArrayList<>(), maracana, new ArrayList<>(), new ArrayList<>(), null);
        Time vasco = new Time(2, "Vasco", new ArrayList<>(), morumbi, new ArrayList<>(), new ArrayList<>(), null);
        // Ensure only Flamengo has Maracanã
        Partida p1 = new Partida(1, LocalDate.of(2025, 5, 21), vasco, flamengo, null, null);
        Campeonato campeonato = new Campeonato(1, 2025, "Copa", new ArrayList<>(), new ArrayList<>(List.of(p1)));
        List<Partida> result = service.filtrarPorEstadio("Maracanã", campeonato);
        assertEquals(1, result.size());
        assertTrue(result.contains(p1));
    }

    @Test
    public void deveFiltrarPorTimeMandante() {
        CampeonatoService service = new CampeonatoService();
        Estadio maracana = new Estadio(1, "Maracanã", "RJ", null);
        Time flamengo = new Time(1, "Flamengo", new ArrayList<>(), maracana, new ArrayList<>(), new ArrayList<>(), null);
        Time vasco = new Time(2, "Vasco", new ArrayList<>(), maracana, new ArrayList<>(), new ArrayList<>(), null);
        Partida p1 = new Partida(1, LocalDate.of(2025, 5, 21), vasco, flamengo, null, null);
        Partida p2 = new Partida(2, LocalDate.of(2025, 5, 21), vasco, flamengo, null, null);
        Campeonato campeonato = new Campeonato(1, 2025, "Copa", new ArrayList<>(), new ArrayList<>(List.of(p1, p2)));
        List<Partida> result = service.filtrarPorTimeMandante("Flamengo", campeonato);
        assertEquals(2, result.size());
        assertTrue(result.contains(p1));
        assertTrue(result.contains(p2));
    }

    @Test
    public void deveCalcularClassificacaoCorretamente() {
        LocalDate futureDate = LocalDate.of(2025, 5, 21); // Usa uma data futura
        Partida partida1 = new Partida(1, futureDate, timeB, timeA, campeonato, new Resultado(2, 1)); // TimeA vence
        Partida partida2 = new Partida(2, futureDate, timeA, timeB, campeonato, new Resultado(1, 1)); // Empate

        campeonatoService.adicionarPartida(campeonato, partida1);
        campeonatoService.adicionarPartida(campeonato, partida2);

        List<Time> classificacao = campeonatoService.calcularClassificacao(campeonato);
        assertEquals(timeA, classificacao.get(0)); // 4 pontos (3 + 1)
        assertEquals(timeB, classificacao.get(1)); // 1 ponto
    }

    @Test
    public void deveAtualizarClassificacaoComEmpate() {
        Partida partida = new Partida(1, LocalDate.now(), timeB, timeA, campeonato, new Resultado(0, 0));
        campeonatoService.adicionarPartida(campeonato, partida);

        assertEquals(1, timeService.getPontos(timeA));
        assertEquals(1, timeService.getPontos(timeB));
    }

    @Test
    public void naoDeveAdicionarPartidaAposInicio() {
        LocalDate dataPassada = LocalDate.now().minusDays(1);
        Partida partidaPassada = new Partida(1, dataPassada, timeB, timeA, campeonato, new Resultado(1, 0));
        campeonatoService.adicionarPartida(campeonato, partidaPassada);

        LocalDate dataFutura = LocalDate.now().plusDays(1);
        Partida novaPartida = new Partida(2, dataFutura, timeA, timeB, campeonato, null);

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> campeonatoService.adicionarPartida(campeonato, novaPartida),
                "Não é possível adicionar partidas após o início do campeonato");
        assertEquals("Não é possível adicionar partidas após o início do campeonato", exception.getMessage());
    }
}