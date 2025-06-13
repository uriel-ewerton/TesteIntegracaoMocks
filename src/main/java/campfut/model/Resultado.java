package campfut.model;

public class Resultado {
    private Integer numGolsMandante;
    private Integer numGolsVisitante;

    public Resultado(Integer numGolsMandante, Integer numGolsVisitante) {
        if (numGolsMandante < 0 || numGolsVisitante < 0) {
            throw new IllegalArgumentException("Número de gols não pode ser negativo");
        }
        this.numGolsMandante = numGolsMandante;
        this.numGolsVisitante = numGolsVisitante;
    }

    public Integer getPontuacaoMandante() {
        // ex.: 3 pontos por vitória, 1 por empate, 0 por derrota
        if (numGolsMandante > numGolsVisitante)  return 3;
        if (numGolsMandante.equals(numGolsVisitante)) return 1;
        return 0;
    }

    public Integer getPontuacaoVisitante() {
        if (numGolsVisitante > numGolsMandante) return 3;
        if (numGolsVisitante.equals(numGolsMandante)) return 1;
        return 0;
    }

    public boolean jogoSaiuEmpatado() {
        return numGolsMandante.equals(numGolsVisitante);
    }


}