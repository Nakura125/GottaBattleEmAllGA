package org.ga.builder;

import org.entity.Pokemon;
import org.ga.problem.PokemonProblem;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2Builder;
import org.uma.jmetal.solution.IntegerSolution;

import java.util.HashMap;
import java.util.List;

public class SPEA2_Builder extends AlgorithmBuilder {

        @Override
        public Algorithm<List<IntegerSolution>> buildAlgorithm(PokemonProblem problem, HashMap<Integer, Pokemon> pokemons) {
                return new SPEA2Builder<>(problem, getCrossoverOperator(), getMutationOperator(problem))
                        .setSelectionOperator(getSelectionOperator())
                        .setMaxIterations(maxIterations)
                        .setPopulationSize(populationSize)
                        .build();
        }
}