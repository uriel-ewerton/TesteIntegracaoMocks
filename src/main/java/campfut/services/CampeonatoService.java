package campfut.services;

import campfut.model.Campeonato;
import campfut.model.Partida;
import campfut.model.Time;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CampeonatoService {

    private final TimeService timeService = new TimeService();

    public void adicionarTime(Campeonato campeonato, Time time) {
        if (campeonato.getTimes().contains(time)) {
            throw new IllegalArgumentException("O time já está no campeonato.");
        }
        campeonato.getTimes().add(time);
        time.setCampeonato(campeonato);
    }

    public void adicionarPartida(Campeonato campeonato, Partida partida) {
        // Verifica se o campeonato já começou (alguma partida tem data <= hoje)
        if (campeonato.getPartidas().stream()
                .anyMatch(p -> p.getData().isBefore(LocalDate.now()) || p.getData().isEqual(LocalDate.now()))) {
            throw new IllegalStateException("Não é possível adicionar partidas após o início do campeonato");
        }
        campeonato.getPartidas().add(partida);
        partida.getTimeMandante().getPartidasComoMandante().add(partida);
        partida.getTimeVisitante().getPartidasComoVisitante().add(partida);
        partida.setCampeonato(campeonato);
    }

    public List<Partida> filtrarPorData(Campeonato campeonato, LocalDate data) {
        if (campeonato == null) throw new IllegalArgumentException("Campeonato cannot be null");
        return Optional.ofNullable(campeonato.getPartidas())
                .orElse(new ArrayList<>()).stream()
                .filter(p -> p.getData().equals(data))
                .collect(Collectors.toList());
    }

    public List<Partida> filtrarPorEstadio(String nomeEstadio, Campeonato campeonato) {
        if (campeonato == null) throw new IllegalArgumentException("Campeonato cannot be null");
        return Optional.ofNullable(campeonato.getPartidas())
                .orElse(new ArrayList<>()).stream()
                .filter(p -> p.getTimeMandante().getSede() != null &&
                        p.getTimeMandante().getSede().getNome().equalsIgnoreCase(nomeEstadio))
                .collect(Collectors.toList());
    }

    public List<Partida> filtrarPorTimeMandante(String nomeTime, Campeonato campeonato) {
        if (campeonato == null) throw new IllegalArgumentException("Campeonato cannot be null");
        return Optional.ofNullable(campeonato.getPartidas())
                .orElse(new ArrayList<>()).stream()
                .filter(p -> p.getTimeMandante().getNome().equalsIgnoreCase(nomeTime))
                .collect(Collectors.toList());
    }

    public List<Time> calcularClassificacao(Campeonato campeonato) {
        return campeonato.getTimes().stream()
                .sorted((t1, t2) -> Integer.compare(timeService.getPontos(t2), timeService.getPontos(t1)))
                .collect(Collectors.toList());
    }
}