package campfut.services;

import campfut.model.Jogador;
import campfut.model.Partida;
import campfut.model.Time;

import java.util.ArrayList;

public class TimeService {

    public void adicionarJogador(Time time, Jogador jogador) {
        if (jogador.getTimeEmQueJoga() != null && jogador.getTimeEmQueJoga() != time) {
            throw new IllegalStateException("Jogador já pertence a outro time");
        }
        time.getJogadores().add(jogador);
        jogador.setTimeEmQueJoga(time);
    }

    public void removerJogador(Time time, Jogador jogador) {
        time.getJogadores().remove(jogador);
        jogador.setTimeEmQueJoga(null);
    }

    public int getPontos(Time time) {
        int pontos = 0;
        for (Partida partida : time.getPartidasComoMandante()) {
            if (partida.getResultado() != null) {
                pontos += partida.getResultado().getPontuacaoMandante();
            }
        }
        for (Partida partida : time.getPartidasComoVisitante()) {
            if (partida.getResultado() != null) {
                pontos += partida.getResultado().getPontuacaoVisitante();
            }
        }
        return pontos;
    }

    public double getAlturaMedia(Time time) {
        ArrayList<Jogador> jogadores = time.getJogadores();
        if (jogadores == null || jogadores.isEmpty()) {
            return 0.0;
        }
        double soma = 0;
        for (Jogador jogador : jogadores) {
            soma += jogador.getAltura();
        }
        return soma / jogadores.size();
    }
}

