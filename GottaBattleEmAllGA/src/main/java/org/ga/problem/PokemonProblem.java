package org.ga.problem;

import org.entity.Mossa;
import org.entity.Pokemon;
import org.repository.MossaRepository;
import org.repository.PokemonRepository;
import org.uma.jmetal.problem.IntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.solution.impl.DefaultIntegerSolution;

import java.util.*;

public class PokemonProblem implements IntegerProblem {

    private static final double MIN_FITNESS = 0;
    private static final double MAX_FITNESS = 100;

    HashMap<Integer, Pokemon> pokemons = new PokemonRepository().getPokemons();

    //max of total
    Integer maxTotal = pokemons.keySet().stream().mapToInt(i -> pokemons.get(i).getTotale()).max().orElseThrow(NoSuchFieldError::new);

    //min of total
    Integer minTotal = pokemons.keySet().stream().mapToInt(i -> pokemons.get(i).getTotale()).min().orElseThrow(NoSuchFieldError::new);

    Integer maxPotenza = new MossaRepository().getMosse().values().stream().mapToInt(Mossa::getPotenza).max().orElseThrow(NoSuchFieldError::new);

    Integer minPotenza = new MossaRepository().getMosse().values().stream().mapToInt(Mossa::getPotenza).min().orElseThrow(NoSuchFieldError::new);

    Integer maxPrecisione = new MossaRepository().getMosse().values().stream().mapToInt(Mossa::getPrecisione).max().orElseThrow(NoSuchFieldError::new);

    Integer minPrecisione = new MossaRepository().getMosse().values().stream().mapToInt(Mossa::getPrecisione).min().orElseThrow(NoSuchFieldError::new);

    Integer maxPP = new MossaRepository().getMosse().values().stream().mapToInt(Mossa::getPp).max().orElseThrow(NoSuchFieldError::new);

    Integer minPP = new MossaRepository().getMosse().values().stream().mapToInt(Mossa::getPp).min().orElseThrow(NoSuchFieldError::new);

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

    private double evaluateTotal(IntegerSolution integerSolution){
        double sumTotal=0;

        for (int i = 0; i < integerSolution.getNumberOfVariables(); i++) {
            sumTotal+=pokemons.get(integerSolution.getVariableValue(i)).getTotale();
        }

        double total = sumTotal/integerSolution.getNumberOfVariables();

        return normalizeFitness(total, minTotal, maxTotal, MIN_FITNESS,MAX_FITNESS);
    }

    private double coverageType(IntegerSolution integerSolution){

        Set<String> types= new HashSet<>();
        for(int i = 0; i < integerSolution.getNumberOfVariables(); i++) {
            types.add(pokemons.get(integerSolution.getVariableValue(i)).getTipo1());
            types.add(pokemons.get(integerSolution.getVariableValue(i)).getTipo2());
        }

        return normalizeFitness(types.size(),1,12,MIN_FITNESS,MAX_FITNESS);
    }

    private double coverageMoveType(IntegerSolution integerSolution){
        Set<String> types= new HashSet<>();
        for(int i = 0; i < integerSolution.getNumberOfVariables(); i++) {
            for (int j = 0; j < 4; j++) {
                if(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j) != null)
                    types.add(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j).getTipo());
            }
        }

        return normalizeFitness(types.size(),1,24,MIN_FITNESS,MAX_FITNESS);
    }

    private double coverageMoveCategory(IntegerSolution integerSolution){
        HashMap<String, Integer> categories = new HashMap<>();

        for(int i = 0; i < integerSolution.getNumberOfVariables(); i++) {
            for (int j = 0; j < 4; j++) {
                if(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j) != null){
                    if(categories.containsKey(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j).getCategoria())){
                        categories.put(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j).getCategoria(), categories.get(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j).getCategoria())+1);
                    }else{
                        categories.put(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j).getCategoria(), 1);
                    }
                }
            }
        }

        //calculate average
        double average=categories.values().stream().mapToInt(Integer::intValue).average().orElse(0);

        //calculate distance from average for each category and sum
        double sum=categories.values().stream().mapToDouble(i -> Math.abs(i-average)).sum();


        return normalizeFitness(-sum,-24,0,MIN_FITNESS,MAX_FITNESS);
    }

    private double evaluateMoveStats(IntegerSolution integerSolution){
        double sumMoves=0;
        for (int i = 0; i < integerSolution.getNumberOfVariables(); i++) {
            for (int j = 0; j < 4; j++) {
                if(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j) != null){
                    double potenza = normalizeFitness(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j).getPotenza(), minPotenza, maxPotenza, MIN_FITNESS,MAX_FITNESS);
                    double precisione = normalizeFitness(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j).getPrecisione(), minPrecisione, maxPrecisione, MIN_FITNESS,MAX_FITNESS);
                    double pp = normalizeFitness(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j).getPp(), minPP, maxPP, MIN_FITNESS,MAX_FITNESS);
                    double sum = potenza+precisione+pp;

                    sumMoves+= normalizeFitness(sum, 0, MAX_FITNESS*3, MIN_FITNESS,MAX_FITNESS);
                }
            }

        }

        return normalizeFitness(sumMoves, 0, MAX_FITNESS*24, MIN_FITNESS,MAX_FITNESS);
    }

    @Override
    public void evaluate(IntegerSolution integerSolution) {
        double[] weights = {1, 0.66, 0.33, 0};

        double total = evaluateTotal(integerSolution);
        double coverageType = coverageType(integerSolution);
        double coverageMoveType = coverageMoveType(integerSolution);
        double coverageMoveCategory = coverageMoveCategory(integerSolution);
        double evaluateMoveStats = evaluateMoveStats(integerSolution);

        integerSolution.setObjective(0, (total * weights[0]));
        integerSolution.setObjective(1, (coverageType * weights[0]));
        integerSolution.setObjective(2, (coverageMoveType * weights[0]));
//        integerSolution.setObjective(3, coverageMoveCategory * weights[0]);
        integerSolution.setObjective(3, (evaluateMoveStats * weights[0]));

        System.out.println(total+ coverageType + coverageMoveType  + evaluateMoveStats);
    }

    @Override
    public IntegerSolution createSolution() {
        // Crea una nuova soluzione IntegerSolution
        IntegerSolution solution = new DefaultIntegerSolution(this);
        // Inizializza le variabili della soluzione con valori casuali all'interno del range consentito
        for (int i = 0; i < getNumberOfVariables(); i++) {

            int randomValue=0;
            do{
             randomValue = (int) (Math.random() * pokemons.size() );
            }while(pokemons.get(randomValue).getTotale()<400);
//            while(pokemons.get(randomValue).getTotale()<600 && Objects.equals(pokemons.get(randomValue).getTipo2(), ""));

            solution.setVariableValue(i, randomValue);
        }


        return solution;
    }

     private double normalizeFitness(double x, double minX, double maxX, double minY, double maxY){
        double normalizedFitness = (x - minX) / (maxX - minX) * (maxY - minY) + minY;
        return Math.max(0, Math.min(100, normalizedFitness));
    }
}
