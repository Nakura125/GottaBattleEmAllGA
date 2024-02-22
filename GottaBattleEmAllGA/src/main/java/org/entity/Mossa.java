package org.entity;

public class Mossa {
    private String nome;
    private String tipo;
    private String categoria;
    private Integer potenza;
    private Integer precisione;
    private Integer pp;

    public Mossa(String nome, String tipo, String categoria, Integer potenza, Integer precisione, Integer pp) {
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.potenza = potenza;
        this.precisione = precisione;
        this.pp = pp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getPotenza() {
        return potenza;
    }

    public void setPotenza(Integer potenza) {
        this.potenza = potenza;
    }

    public Integer getPrecisione() {
        return precisione;
    }

    public void setPrecisione(Integer precisione) {
        this.precisione = precisione;
    }

    public Integer getPp() {
        return pp;
    }

    public void setPp(Integer pp) {
        this.pp = pp;
    }

    public String toString() {
        return "Mossa: " + nome + " di tipo " + tipo + " di categoria " + categoria + " con potenza " + potenza + " con precisione " + precisione + " con pp " + pp;
    }
}
