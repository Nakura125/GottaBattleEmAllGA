package org.entity;

import java.util.ArrayList;

import java.util.List;
public class Pokemon {
    private String nome;
    private String tipo1;
    private String tipo2;
    private Integer totale;

    private String forma;
    List<Mossa> mosse = new ArrayList<Mossa>();

    //costructor
    public Pokemon(String nome, String tipo1, String tipo2, Integer totale, String forma,Mossa mossa1, Mossa mossa2, Mossa mossa3, Mossa mossa4) {
        this.nome = nome;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.totale = totale;
        this.forma= forma;
        this.mosse.add(mossa1);
        this.mosse.add(mossa2);
        this.mosse.add(mossa3);
        this.mosse.add(mossa4);
    }

    //getter e setter


    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public void setMosse(List<Mossa> mosse) {
        this.mosse = mosse;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo1() {
        return tipo1;
    }

    public String getTipo2() {
        return tipo2;
    }

    public Integer getTotale() {
        return totale;
    }

    //get Mosse
    public List<Mossa> getMosse() {
        return mosse;
    }

    //get Mosse
    public Mossa getMossa(int i){
        return mosse.get(i);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo1(String tipo1) {
        this.tipo1 = tipo1;
    }

    public void setTipo2(String tipo2) {
        this.tipo2 = tipo2;
    }

    public void setTotale(Integer totale) {
        this.totale = totale;
    }


    @Override
    public String toString() {
        return "Pokemon{" +
                "nome='" + nome + '\'' +
                ", tipo1='" + tipo1 + '\'' +
                ", tipo2='" + tipo2 + '\'' +
                ", totale=" + totale +
                '}';
    }
}
