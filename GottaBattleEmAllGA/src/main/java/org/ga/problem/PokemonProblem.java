package org.ga.problem;

import org.entity.Mossa;
import org.entity.Pokemon;
import org.ga.fitness.PokemonFitness;
import org.repository.MossaRepository;
import org.repository.PokemonRepository;
import org.uma.jmetal.problem.IntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.solution.impl.DefaultIntegerSolution;

import java.util.*;

public class PokemonProblem implements IntegerProblem {


    HashMap<Integer, Pokemon> pokemons = PokemonFitness.pokemons;


    @Override
    public Integer getLowerBound(int i) {
        return 0;
    }

    @Override
    public Integer getUpperBound(int i) {
        return pokemons.size()-1;
    }

    @Override
    public int getNumberOfVariables() {
        return 6;
    }

    @Override
    public int getNumberOfObjectives() {
        return 4;
    }

    @Override
    public int getNumberOfConstraints() {
        return 0;
    }

    @Override
    public String getName() {
        return "PokemonProblem";
    }



    @Override
    public void evaluate(IntegerSolution integerSolution) {
        double[] weights = {1.25, 1, 0.5};

        double total = PokemonFitness.evaluateTotal(integerSolution);
        double coverageType = PokemonFitness.coverageType(integerSolution);
        double coverageMoveType = PokemonFitness.coverageMoveType(integerSolution);
//        double coverageMoveCategory = PokemonFitness.coverageMoveCategory(integerSolution);
        double evaluateMoveStats = PokemonFitness.evaluateMoveStats(integerSolution);

        integerSolution.setObjective(0, (total * weights[0]));
        integerSolution.setObjective(1, (coverageType * weights[0]));
        integerSolution.setObjective(2, (coverageMoveType * weights[1]));
        integerSolution.setObjective(3, (evaluateMoveStats * weights[2]));

//        System.out.println(total+ coverageType + coverageMoveType  + evaluateMoveStats);
    }

    @Override
    public IntegerSolution createSolution() {
        // Crea una nuova soluzione IntegerSolution
        IntegerSolution solution = new DefaultIntegerSolution(this);
        // Inizializza le variabili della soluzione con valori casuali all'interno del range consentito
        for (int i = 0; i < getNumberOfVariables(); i++) {

            int randomValue=0;
//            do{
             randomValue = (int) (Math.random() * pokemons.size() );
//            }while(pokemons.get(randomValue).getTotale()<400 && Objects.equals(pokemons.get(randomValue).getTipo2(), ""));
//            while(pokemons.get(randomValue).getTotale()<600 && Objects.equals(pokemons.get(randomValue).getTipo2(), ""));

            solution.setVariableValue(i, randomValue);
        }


        return solution;
    }


}
