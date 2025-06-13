package campfut.model;


import java.time.LocalDate;


public class Jogador {
    private Integer id;
    private LocalDate dataNascimento;
    private String nome;
    private String genero;
    private Float altura;

    private Time timeEmQueJoga;

    public Jogador(Integer id, LocalDate dataNascimento, String nome, String genero, Float altura, Time timeEmQueJoga) {
        this.id = id;
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.genero = genero;
        this.altura = altura;
        this.timeEmQueJoga = timeEmQueJoga;
    }

    public Jogador() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if(dataNascimento.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("A data não deve ser depois da data atual.");
        }
        if(dataNascimento.isBefore(LocalDate.now().minusYears(120))){
            throw new IllegalArgumentException("A data não deve ser antiga de forma impossivel.");
        }
        this.dataNascimento = dataNascimento;
    }



    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        if(altura <= 0){
            throw new IllegalArgumentException("Altura deve ser maior que zero");
        }
        this.altura = altura;
    }

    public Integer getIdade() {
        if (dataNascimento == null) return 0;
        return (int) java.time.temporal.ChronoUnit.YEARS.between(dataNascimento, java.time.LocalDate.now());
    }

    public Time getTimeEmQueJoga() {
        return timeEmQueJoga;
    }

    public void setTimeEmQueJoga(Time timeEmQueJoga) {
        this.timeEmQueJoga = timeEmQueJoga;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
