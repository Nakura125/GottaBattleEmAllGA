package org.ga.builder;

import org.ga.problem.PokemonProblem;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.crossover.IntegerSBXCrossover;
import org.uma.jmetal.operator.impl.mutation.IntegerPolynomialMutation;
import org.uma.jmetal.operator.impl.selection.TournamentSelection;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import org.uma.jmetal.util.comparator.FitnessComparator;
import org.uma.jmetal.util.evaluator.impl.MultithreadedSolutionListEvaluator;

import java.util.List;

public class NSGAIITest1 implements AlgorithmBuilder<NSGAII<IntegerSolution>> {

    @Override
    public NSGAII<IntegerSolution> build() {
        PokemonProblem pokemonProblem=new PokemonProblem();

        CrossoverOperator<IntegerSolution> crossoverOperator= new IntegerSBXCrossover(1,6);
        MutationOperator<IntegerSolution> mutationOperator= new IntegerPolynomialMutation( 0.1,1);
        SelectionOperator<List<IntegerSolution>,IntegerSolution> selectionOperator= new TournamentSelection<>(new FitnessComparator<>(),10);
        return new NSGAII<IntegerSolution>(pokemonProblem,
                100, //numero massimo di iterazione
                200, // numero di individui nella popolazione
                200, // numero di individui selezionati ad ogni iterazione
                500, // numero di individui selezionati per il fronte di parteto
                crossoverOperator,
                mutationOperator,
                selectionOperator,
                new MultithreadedSolutionListEvaluator<IntegerSolution>(8,pokemonProblem) );
    }
}
