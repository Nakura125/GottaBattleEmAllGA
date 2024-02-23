package org.ga.builder;


import org.entity.Pokemon;
import org.ga.problem.PokemonProblem;
import org.repository.PokemonRepository;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.AlgorithmRunner;

import java.util.HashMap;
import java.util.List;

public class TestRun {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Ottenere la mappa dei pokémon
        HashMap<Integer, Pokemon> pokemons = new PokemonRepository().getPokemons();

        // Creare un'istanza del problema PokemonProblem
        PokemonProblem pokemonProblem = new PokemonProblem();


/*        // Creare un'istanza di NSGAII_Builder e ottenere l'algoritmo
        NSGAII_Builder nsgaII_Builder = new NSGAII_Builder();
        Algorithm<List<IntegerSolution>> algorithmNSGAII = nsgaII_Builder.buildAlgorithm(pokemonProblem, pokemons);


        // Eseguire l'algoritmo e stampare i risultati
        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithmNSGAII)
                .execute();
        List<IntegerSolution> population = algorithmNSGAII.getResult();
        nsgaII_Builder.printResults(population, pokemons);*/

        // Creare un'istanza di SPEA2_Builder e ottenere l'algoritmo
        SPEA2_Builder spea2_Builder = new SPEA2_Builder();
        Algorithm<List<IntegerSolution>> algorithmSPEA2 = spea2_Builder.buildAlgorithm(pokemonProblem, pokemons);

        // Eseguire l'algoritmo e stampare i risultati
        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithmSPEA2)
                .execute();
        List<IntegerSolution> population = algorithmSPEA2.getResult();
        spea2_Builder.printResults(population, pokemons);

    }
}
