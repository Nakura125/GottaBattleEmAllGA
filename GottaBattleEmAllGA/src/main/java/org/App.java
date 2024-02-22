package org;

import org.entity.Pokemon;
import org.repository.PokemonRepository;

import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        HashMap<Integer, Pokemon> pokemons = new PokemonRepository().getPokemons();

        for (Integer i : pokemons.keySet()) {
            System.out.println(pokemons.get(i).getNome());
            for (int j = 0; j < 5; j++) {
                if (pokemons.get(i).getMossa(j) != null)
                    System.out.println("\t"+pokemons.get(i).getMossa(j).getNome());
            }
        }
    }
}
