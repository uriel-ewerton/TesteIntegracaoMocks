package campfut.model;

import java.util.ArrayList;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Campeonato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer ano;
    private String nome;

    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL)
    private List<Time> times;

    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL)
    private List<Partida> partidas;
    public Campeonato(Integer id, Integer ano, String nome, List<Time> times, List<Partida> partidas) {
        this.id = id;
        this.ano = ano;
        this.nome = nome;
        this.times = times;
        this.partidas = partidas;
    }

    public Campeonato() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Time> getTimes() {
        return times;
    }

    public void setTimes(List<Time> times) {
        this.times = times;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campeonato campeonato = (Campeonato) o;
        return id != null && id.equals(campeonato.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    public void adicionarTime(Time time) {
        if (times == null) {
            times = new ArrayList<>();
        }

        if (times.contains(time)) {
            throw new IllegalArgumentException("O time já está no campeonato.");
        }

        times.add(time);
    }
}
