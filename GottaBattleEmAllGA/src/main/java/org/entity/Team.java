package org.entity;

import java.io.Serializable;
import java.util.List;
public class Team implements Serializable {

    Integer i=0;
    private Pokemon[] pokemons= new Pokemon[6];

    public void addPokemon(Pokemon pokemon){
        if(i<6){
            pokemons[i]=pokemon;
            i++;
        }
    }

    //get pokemons
    public Pokemon[] getPokemons() {
        return pokemons;
    }

    //get pokemon
    public Pokemon getPokemon(int i) {
        if (i<0 || i>5) return null;
        return pokemons[i];
    }

    //check if is full
    public boolean isFull(){
        return i==6;
    }
}
