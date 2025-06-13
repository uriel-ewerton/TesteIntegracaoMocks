package campfut.model;

public class Estadio {
    private Integer id;
    private String nome;
    private String endereco;

    private Time time;

    public Estadio(Integer id, String nome, String endereco, Time time) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.time = time;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
