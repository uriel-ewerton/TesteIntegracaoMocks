package campfut.model;

import jakarta.persistence.*;
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

    public Time(Integer id, String nome, ArrayList<Jogador> jogadores, Estadio sede, ArrayList<Partida> partidasComoMandante, ArrayList<Partida> partidasComoVisitante, Campeonato campeonato) {
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

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public Estadio getSede() {
        return sede;
    }

    public void setSede(Estadio sede) {
        this.sede = sede;
    }

    public ArrayList<Partida> getPartidasComoMandante() {
        return partidasComoMandante;
    }

    public void setPartidasComoMandante(ArrayList<Partida> partidasComoMandante) {
        this.partidasComoMandante = partidasComoMandante;
    }

    public ArrayList<Partida> getPartidasComoVisitante() {
        return partidasComoVisitante;
    }

    public void setPartidasComoVisitante(ArrayList<Partida> partidasComoVisitante) {
        this.partidasComoVisitante = partidasComoVisitante;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }
}
