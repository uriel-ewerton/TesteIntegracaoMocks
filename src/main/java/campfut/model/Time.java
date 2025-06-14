package campfut.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @OneToMany(mappedBy = "timeEmQueJoga", cascade = CascadeType.ALL)
    private List<Jogador> jogadores;

    @OneToOne
    private Estadio sede;

    @OneToMany(mappedBy = "timeMandante", cascade = CascadeType.ALL)
    private List<Partida> partidasComoMandante;

    @OneToMany(mappedBy = "timeVisitante", cascade = CascadeType.ALL)
    private List<Partida> partidasComoVisitante;

    @ManyToOne
    private Campeonato campeonato;

    public Time(Integer id, String nome, List<Jogador> jogadores, Estadio sede, List<Partida> partidasComoMandante, List<Partida> partidasComoVisitante, Campeonato campeonato) {
        this.id = id;
        this.nome = nome;
        this.jogadores = jogadores;
        this.sede = sede;
        this.partidasComoMandante = partidasComoMandante;
        this.partidasComoVisitante = partidasComoVisitante;
        this.campeonato = campeonato;
    }

    public Time() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public Estadio getSede() {
        return sede;
    }

    public void setSede(Estadio sede) {
        this.sede = sede;
    }

    public List<Partida> getPartidasComoMandante() {
        return partidasComoMandante;
    }

    public void setPartidasComoMandante(List<Partida> partidasComoMandante) {
        this.partidasComoMandante = partidasComoMandante;
    }

    public List<Partida> getPartidasComoVisitante() {
        return partidasComoVisitante;
    }

    public void setPartidasComoVisitante(List<Partida> partidasComoVisitante) {
        this.partidasComoVisitante = partidasComoVisitante;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }
}
