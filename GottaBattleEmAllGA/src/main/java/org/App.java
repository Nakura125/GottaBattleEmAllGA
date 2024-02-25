package org;

import org.distance.Prediction;
import org.entity.Pokemon;
import org.entity.Team;
import org.ga.problem.PokemonProblem;
import org.repository.PokemonRepository;


import java.util.HashMap;
import java.util.List;


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

        // inizializzazione due team a caso
        Team team1 = new Team();
        Team team2 = new Team();
        for(int i=0; i<6; i++){
            team1.addPokemon(pokemons.get(i));
            team2.addPokemon(pokemons.get(i*36+1));
        }
        System.out.println("Team1: ");
        for (int i = 0; i < 6; i++) {
            if (team1.getPokemon(i) != null)
                System.out.println("\t"+team1.getPokemon(i).getNome());
        }

        System.out.println("Team2: ");
        for (int i = 0; i < 6; i++) {
            if (team2.getPokemon(i) != null)
                System.out.println("\t"+team2.getPokemon(i).getNome());
        }

        //calcolo della distanza tra i due team
        System.out.println("Predizione per team1: " + Prediction.predict(team1, team2));
//        System.out.println("Predizione per team2: " + Prediction.predict(team2, team1));


    }
}
