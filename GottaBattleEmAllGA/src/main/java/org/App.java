package org;

import org.entity.Mossa;
import org.entity.Pokemon;
import org.ga.builder.NSGAIITest1;
import org.ga.problem.PokemonProblem;
import org.repository.PokemonRepository;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.crossover.IntegerSBXCrossover;
import org.uma.jmetal.operator.impl.crossover.NPointCrossover;
import org.uma.jmetal.operator.impl.mutation.IntegerPolynomialMutation;
import org.uma.jmetal.operator.impl.selection.TournamentSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.ProblemUtils;
import org.uma.jmetal.util.comparator.FitnessComparator;

import java.util.HashMap;
import java.util.List;

import static org.uma.jmetal.util.AbstractAlgorithmRunner.printFinalSolutionSet;

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

//        for (Integer i : pokemons.keySet()) {
//            System.out.println(pokemons.get(i).getNome());
//            for (int j = 0; j < 4; j++) {
//                if (pokemons.get(i).getMossa(j) != null)
//                    System.out.println("\t"+pokemons.get(i).getMossa(j).getNome());
//            }
//        }
//
//        Integer maxTotal = pokemons.keySet().stream().mapToInt(i -> pokemons.get(i).getTotale()).max().orElseThrow(NoSuchFieldError::new);
//        System.out.println("maxTotal: "+maxTotal);
//
//        Integer minTotal = pokemons.keySet().stream().mapToInt(i -> pokemons.get(i).getTotale()).min().orElseThrow(NoSuchFieldError::new);
//        System.out.println("minTotal: "+minTotal);
//
//        System.out.println("normalizedFitness: "+normalizeFitness(0, -16, 0, 0, 100));
//
//        NSGAIITest1 test1 = new NSGAIITest1();
//
//        NSGAII<IntegerSolution> algorithm= test1.build();
//
////        AlgorithmRunner a= new AlgorithmRunner.Executor(algorithm.b).execute();
//
//
//
//        List<IntegerSolution> solutions= algorithm.getResult();
//
//        int index=0;
//        for (IntegerSolution solution : solutions) {
//            System.out.println("Team  "+ index + ":");
//            for (int i = 0; i < solution.getNumberOfVariables(); i++) {
////                System.out.println("\tVariable " + i + " value: " +  pokemons.get(solution.getVariableValue(i))) ;
//            }
//            double sum=0;
//
//            for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
////                System.out.println("\tObjective " + i + " value: " + solution.getObjective(i)) ;
//                sum+=solution.getObjective(i);
//            }
//
//            //print moves
////            for (int i = 0; i < solution.getNumberOfVariables(); i++) {
////                System.out.println("\tMoves for " + pokemons.get(solution.getVariableValue(i)).getNome() + ":");
////                for (int j = 0; j < 4; j++) {
////                    if (pokemons.get(solution.getVariableValue(i)).getMossa(j) != null)
////                        System.out.println("\t\t"+pokemons.get(solution.getVariableValue(i)).getMossa(j).getCategoria());
////                }
////            }
//
//            System.out.println("\tSum of objectives: " + sum);
//            index++;
//        }

        Algorithm<List<IntegerSolution>> algorithm2;
        PokemonProblem pokemonProblem=new PokemonProblem();



        CrossoverOperator<IntegerSolution> crossoverOperator= new IntegerSBXCrossover(0.8,6);
        MutationOperator<IntegerSolution> mutationOperator= new IntegerPolynomialMutation(0.3,7);
        SelectionOperator<List<IntegerSolution>,IntegerSolution> selectionOperator= new TournamentSelection<>(5);

        algorithm2 = new NSGAIIBuilder<IntegerSolution>(pokemonProblem, crossoverOperator, mutationOperator,100)
                .setSelectionOperator(selectionOperator)
                .setMaxEvaluations(200)
                .build() ;



        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm2).execute() ;

        List<IntegerSolution> population = algorithm2.getResult() ;
        long computingTime = algorithmRunner.getComputingTime() ;

        JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");

        printFinalSolutionSet(population);

        int index=0;
        for(IntegerSolution solution: population){
            System.out.println("Team : "+index);
            //stampa pokemon
            for (int i = 0; i < solution.getNumberOfVariables(); i++) {
                System.out.println("\tVariable " + i + " value: " +  pokemons.get(solution.getVariableValue(i)).getNome()) ;

            }

            System.out.println("Objective 0: "+solution.getObjective(0));
            System.out.println("Objective 1: "+solution.getObjective(1));
            System.out.println("Objective 2: "+solution.getObjective(2));
            System.out.println("Objective 3: "+solution.getObjective(3));

            double sum=solution.getObjective(0)+ solution.getObjective(1) + solution.getObjective(2) + solution.getObjective(3);

            System.out.println("sum : " +sum);

            index++;
        }

//        if (!referenceParetoFront.equals("")) {
//            printQualityIndicators(population, referenceParetoFront) ;
//        }

    }
}
