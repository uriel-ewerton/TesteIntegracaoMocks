package campfut.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate data;

    @ManyToOne
    private Time timeMandante;

    @ManyToOne
    private Time timeVisitante;

    @ManyToOne
    private Campeonato campeonato;

    @Embedded
    private Resultado resultado;

    public Partida(Integer id, LocalDate data, Time timeVisitante, Time timeMandante, Campeonato campeonato, Resultado resultado) {
        this.id = id;
        this.data = data;
        this.timeVisitante = timeVisitante;
        this.timeMandante = timeMandante;
        this.campeonato = campeonato;
        this.resultado = resultado;
    }

    public Partida() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Time getTimeVisitante() {
        return timeVisitante;
    }

    public void setTimeVisitante(Time timeVisitante) {
        this.timeVisitante = timeVisitante;
    }

    public Time getTimeMandante() {
        return timeMandante;
    }

    public void setTimeMandante(Time timeMandante) {
        this.timeMandante = timeMandante;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }
}
