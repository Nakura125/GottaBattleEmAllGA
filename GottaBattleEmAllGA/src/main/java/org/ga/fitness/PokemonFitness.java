package org.ga.fitness;

import org.entity.Mossa;
import org.entity.Pokemon;
import org.repository.MossaRepository;
import org.repository.PokemonRepository;
import org.uma.jmetal.solution.IntegerSolution;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PokemonFitness {

    private static final double MIN_FITNESS = 0;
    private static final double MAX_FITNESS = 100;

    public static final HashMap<Integer, Pokemon> pokemons = new PokemonRepository().getPokemons();

    //max of total
    private static final Integer maxTotal = pokemons.keySet().stream().mapToInt(i -> pokemons.get(i).getTotale()).max().orElseThrow(NoSuchFieldError::new);

    //min of total
    private static final Integer minTotal = pokemons.keySet().stream().mapToInt(i -> pokemons.get(i).getTotale()).min().orElseThrow(NoSuchFieldError::new);

    private static final Integer maxPotenza = new MossaRepository().getMosse().values().stream().mapToInt(Mossa::getPotenza).max().orElseThrow(NoSuchFieldError::new);

    private static final Integer minPotenza = new MossaRepository().getMosse().values().stream().mapToInt(Mossa::getPotenza).min().orElseThrow(NoSuchFieldError::new);

    private static final Integer maxPrecisione = new MossaRepository().getMosse().values().stream().mapToInt(Mossa::getPrecisione).max().orElseThrow(NoSuchFieldError::new);

    private static final Integer minPrecisione = new MossaRepository().getMosse().values().stream().mapToInt(Mossa::getPrecisione).min().orElseThrow(NoSuchFieldError::new);

    private static final Integer maxPP = new MossaRepository().getMosse().values().stream().mapToInt(Mossa::getPp).max().orElseThrow(NoSuchFieldError::new);

    private static final Integer minPP = new MossaRepository().getMosse().values().stream().mapToInt(Mossa::getPp).min().orElseThrow(NoSuchFieldError::new);


    public static double evaluateTotal(IntegerSolution integerSolution){
        double sumTotal=0;

        for (int i = 0; i < integerSolution.getNumberOfVariables(); i++) {
            double value=0;
            if(pokemons.get(integerSolution.getVariableValue(i)).getTotale()>600)
                value=600;
            else
                value=pokemons.get(integerSolution.getVariableValue(i)).getTotale();
            sumTotal+=value;
        }

        double total = sumTotal/integerSolution.getNumberOfVariables();

        return normalizeFitness(total, minTotal, 600, MIN_FITNESS,MAX_FITNESS);
    }

    public static  double coverageType(IntegerSolution integerSolution){

        Set<String> types= new HashSet<>();
        for(int i = 0; i < integerSolution.getNumberOfVariables(); i++) {
            types.add(pokemons.get(integerSolution.getVariableValue(i)).getTipo1());
            types.add(pokemons.get(integerSolution.getVariableValue(i)).getTipo2());
        }

        return normalizeFitness(types.size(),1,12,MIN_FITNESS,MAX_FITNESS);
    }

    public static  double coverageMoveType(IntegerSolution integerSolution){
        double[] typesCount = {10,35,100,75};

        double value=0;
        for(int i = 0; i < integerSolution.getNumberOfVariables(); i++) {
            Set<String> types= new HashSet<>();
            for (int j = 0; j < 4; j++) {
                if(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j) != null)
                {
                    types.add(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j).getTipo());
                }
            }

            switch (types.size()){
                case 1:
                    value+=typesCount[0];
                    break;
                case 2:
                    value+=typesCount[1];
                    break;
                case 3:
                    value+=typesCount[2];
                    break;
                case 4:
                    value+=typesCount[3];
                    break;
            }

        }

        return normalizeFitness(value,60,600,MIN_FITNESS,MAX_FITNESS);
    }

//    public static  double coverageMoveCategory(IntegerSolution integerSolution){
//        HashMap<String, Integer> categories = new HashMap<>();
//
//        for(int i = 0; i < integerSolution.getNumberOfVariables(); i++) {
//            for (int j = 0; j < 4; j++) {
//                if(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j) != null){
//                    if(categories.containsKey(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j).getCategoria())){
//                        categories.put(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j).getCategoria(), categories.get(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j).getCategoria())+1);
//                    }else{
//                        categories.put(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j).getCategoria(), 1);
//                    }
//                }
//            }
//        }
//
//        //calculate average
//        double average=categories.values().stream().mapToInt(Integer::intValue).average().orElse(0);
//
//        //calculate distance from average for each category and sum
//        double sum=categories.values().stream().mapToDouble(i -> Math.abs(i-average)).sum();
//
//
//        return normalizeFitness(-sum,-24,0,MIN_FITNESS,MAX_FITNESS);
//    }

    public static  double evaluateMoveStats(IntegerSolution integerSolution){
        double sumMoves=0;
        for (int i = 0; i < integerSolution.getNumberOfVariables(); i++) {
            for (int j = 0; j < 4; j++) {
                if(pokemons.get(integerSolution.getVariableValue(i)).getMossa(j) != null){
                    double potenza=pokemons.get(integerSolution.getVariableValue(i)).getMossa(j).getPotenza();
                    double precisione = pokemons.get(integerSolution.getVariableValue(i)).getMossa(j).getPrecisione();
                    double pp = pokemons.get(integerSolution.getVariableValue(i)).getMossa(j).getPp();

                    if(potenza >= 90)
                        potenza=150;
                    if(precisione >= 75)
                        precisione=75;
                    if(pp >= 10)
                        pp=75;

                    double sum = potenza+precisione+pp;

                    sumMoves+= normalizeFitness(sum, 0, 300, MIN_FITNESS,MAX_FITNESS);
                }
            }

        }

        return normalizeFitness(sumMoves, 0, 100*24, MIN_FITNESS,MAX_FITNESS);
    }


    public static  double normalizeFitness(double x, double minX, double maxX, double minY, double maxY){
        double normalizedFitness = (x - minX) / (maxX - minX) * (maxY - minY) + minY;
        return Math.max(0, Math.min(100, normalizedFitness));
    }
}
