package org.ga.builder;

import org.entity.Pokemon;
import org.ga.problem.PokemonProblem;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.solution.IntegerSolution;

import java.util.HashMap;
import java.util.List;

public class NSGAII_Builder extends AlgorithmBuilder {

    @Override
    public Algorithm<List<IntegerSolution>> buildAlgorithm(PokemonProblem problem, HashMap<Integer, Pokemon> pokemons) {
        return new NSGAIIBuilder<>(problem,getCrossoverOperator(), getMutationOperator(problem),populationSize)
                .setSelectionOperator(getSelectionOperator())
                .setMaxEvaluations(maxIterations)
                .setMatingPoolSize(matingPoolSize)
                .build();
    }
    }
