package campfut.model;

import java.util.ArrayList;

public class Campeonato {
    private Integer id;
    private Integer ano;
    private String nome;

    private ArrayList<Time> times = new java.util.ArrayList<>();
    private ArrayList<Partida> partidas = new java.util.ArrayList<>();

    public Campeonato(Integer id, Integer ano, String nome, ArrayList<Time> times, ArrayList<Partida> partidas) {
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

    public ArrayList<Time> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<Time> times) {
        this.times = times;
    }

    public ArrayList<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(ArrayList<Partida> partidas) {
        this.partidas = partidas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return id != null && id.equals(time.getId());
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
