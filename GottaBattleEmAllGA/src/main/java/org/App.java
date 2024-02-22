package org;

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
import org.uma.jmetal.operator.impl.mutation.IntegerPolynomialMutation;
import org.uma.jmetal.operator.impl.selection.TournamentSelection;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.JMetalLogger;
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
    private static double normalizeFitness(double x, double minX, double maxX, double minY, double maxY){
        double normalizedFitness = (x - minX) / (maxX - minX) * (maxY - minY) + minY;
        return Math.max(0, Math.min(100, normalizedFitness));
    }

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
        CrossoverOperator<IntegerSolution> crossoverOperator= new IntegerSBXCrossover(1,6);
        MutationOperator<IntegerSolution> mutationOperator= new IntegerPolynomialMutation( 1,20);
        SelectionOperator<List<IntegerSolution>,IntegerSolution> selectionOperator= new TournamentSelection<>(new FitnessComparator<>(),10);

        algorithm2 = new NSGAIIBuilder<IntegerSolution>(pokemonProblem, crossoverOperator, mutationOperator,1000)
                .setSelectionOperator(selectionOperator)
                .setMaxEvaluations(25000)
                .build() ;



        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm2).execute() ;

        List<IntegerSolution> population = algorithm2.getResult() ;
        long computingTime = algorithmRunner.getComputingTime() ;

        JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");

        printFinalSolutionSet(population);
//        if (!referenceParetoFront.equals("")) {
//            printQualityIndicators(population, referenceParetoFront) ;
//        }

    }
}
