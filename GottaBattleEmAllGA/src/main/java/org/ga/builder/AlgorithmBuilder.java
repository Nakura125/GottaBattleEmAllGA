package org.ga.builder;

import org.entity.Pokemon;
import org.entity.Mossa;
import org.ga.problem.PokemonProblem;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.crossover.IntegerSBXCrossover;
import org.uma.jmetal.operator.impl.crossover.NPointCrossover;
import org.uma.jmetal.operator.impl.mutation.IntegerPolynomialMutation;
import org.uma.jmetal.operator.impl.mutation.PermutationSwapMutation;
import org.uma.jmetal.operator.impl.mutation.UniformMutation;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.operator.impl.selection.RandomSelection;
import org.uma.jmetal.operator.impl.selection.TournamentSelection;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.comparator.FitnessComparator;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public abstract class AlgorithmBuilder {

    protected double crossoverProbability = 1;
    protected double crossoverDistributionIndex = 0.0;
    protected double mutationProbability;
    protected double mutationDistributionIndex = 1.0;
    protected int maxIterations = 100;
    protected int populationSize = 100;

    protected int matingPoolSize = 50;

    public abstract Algorithm<List<IntegerSolution>> buildAlgorithm(PokemonProblem problem, HashMap<Integer, Pokemon> pokemons);

    protected CrossoverOperator<IntegerSolution> getCrossoverOperator() {
        return new IntegerSBXCrossover(crossoverProbability, crossoverDistributionIndex);
//        return new NPointCrossover(3);
    }

    protected MutationOperator<IntegerSolution> getMutationOperator(PokemonProblem problem) {
        mutationProbability = 1.0 / problem.getNumberOfVariables();
//        return new IntegerPolynomialMutation(mutationProbability, mutationDistributionIndex);
//        return new IntegerPolynomialMutation(1, mutationDistributionIndex);
          return new PermutationSwapMutation(1);

    }

    protected SelectionOperator<List<IntegerSolution>, IntegerSolution> getSelectionOperator() {
//        return new BinaryTournamentSelection<IntegerSolution>(new RankingAndCrowdingDistanceComparator<IntegerSolution>());
        return new TournamentSelection<>(5);
    }

    public void printResults(List<IntegerSolution> population, HashMap<Integer, Pokemon> pokemons) {
        // Ordina la popolazione in base alla somma degli obiettivi
//        population.sort(Comparator.comparingDouble(this::calculateSum));

        int index = 0;
        for (IntegerSolution solution : population) {
            System.out.println("Team : " + index);
            // Stampa pokemon
            for (int i = 0; i < solution.getNumberOfVariables(); i++) {
                int pokemonIndex = solution.getVariableValue(i);
                Pokemon pokemon = pokemons.get(pokemonIndex);
                System.out.println("\tPokemon: " + pokemon.getNome());
                // Stampa le mosse del Pokémon
                for (int j = 0; j < 4; j++) {
                    Mossa mossa = pokemon.getMossa(j);
                    if (mossa != null) {
                        System.out.println("\t\tMossa " + (j+1) + ": " + mossa.getNome());
                    }
                }
            }

            System.out.println("Objective 0: " + solution.getObjective(0));
            System.out.println("Objective 1: " + solution.getObjective(1));
            System.out.println("Objective 2: " + solution.getObjective(2));
            System.out.println("Objective 3: " + solution.getObjective(3));

            double sum = calculateSum(solution);
            System.out.println("Sum : " + sum);

            index++;
        }
    }

    private double calculateSum(IntegerSolution solution) {
        double sum = 0;
        for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
            sum += solution.getObjective(i);
        }
        return sum;
    }
}