package org.repository;

import org.entity.Pokemon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class PokemonRepository implements  Repository{

    HashMap<Integer, Pokemon> pokemons = new HashMap<Integer, Pokemon>();

    //constructor
    public PokemonRepository(){
        read();
    }

    // Inizializzazione del dataset
    public void read() {
       String csvFile = "Dataset/Pokedex_V2.csv";
       String line = "";
       MossaRepository mosse = new MossaRepository();

       try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            Integer i=0;
           line = br.readLine();
           while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                //logica di inserimento
                pokemons.put(i,
                        new Pokemon(
                                data[1],//nome
                                data[3],//tipo1
                                data[4],//tipo2
                                Integer.parseInt(data[13]),//totale
                                mosse.getMossa(data[15]),//mosse
                                mosse.getMossa(data[16]),//mosse
                                mosse.getMossa(data[17]),//mosse
                                mosse.getMossa(data[18])));//mosse


                //incrementare l'indice
                i++;
            }
       } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // get Pokemons
    public HashMap<Integer, Pokemon> getPokemons() {
        return pokemons;
    }


    //getPokemon
    public Pokemon getPokemon(int id){
        return pokemons.get(id);
    }

}
