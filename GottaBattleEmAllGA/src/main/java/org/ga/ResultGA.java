package org.ga;

import org.entity.Pokemon;
import org.entity.Team;
import org.ga.builder.AlgorithmBuilder;
import org.ga.builder.NSGAII_Builder;
import org.ga.builder.SPEA2_Builder;
import org.ga.problem.PokemonProblem;
import org.repository.PokemonRepository;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.AlgorithmRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ResultGA {

    public static List<Team> getResult(){
        HashMap<Integer, Pokemon> pokemons = new PokemonRepository().getPokemons();

        // Creare un'istanza del problema PokemonProblem
        PokemonProblem pokemonProblem = new PokemonProblem();

        NSGAII_Builder nsga2_Builder = new NSGAII_Builder();
        Algorithm<List<IntegerSolution>> algorithm =
                nsga2_Builder.buildAlgorithm(pokemonProblem, pokemons)
                ;

        // Eseguire l'algoritmo e stampare i risultati
        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm)
                .execute();
        List<IntegerSolution> population = algorithm.getResult();
        population.sort(Comparator.comparingDouble(AlgorithmBuilder::calculateSum));
        List<Team> result= new ArrayList<>();

        for (IntegerSolution solution : population) {
            Team team = new Team();
            for (int j = 0; j < solution.getNumberOfVariables(); j++) {
                team.addPokemon(pokemons.get(solution.getVariableValue(j)));
            }
            result.add(team);

            System.out.println("Fitness:"+AlgorithmBuilder.calculateSum(solution));
        }

//        int start = Math.max(0, population.size() - 10);
//        for(int i = population.size() - 1; i >= start; i--){
//            IntegerSolution solution = population.get(i);
//            Team team = new Team();
//            System.out.println("Fitness:"+AlgorithmBuilder.calculateSum(solution));
//            for(int j = 0; j < solution.getNumberOfVariables(); j++){
//                team.addPokemon(pokemons.get(solution.getVariableValue(j)));
//            }
//            result.add(team);
//        }

        return result;
    }
}
