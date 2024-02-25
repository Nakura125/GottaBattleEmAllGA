package org.distance;

import org.entity.Team;
import org.ga.ResultGA;
import org.ga.SaveGA;

import java.util.List;
import java.util.Objects;

public class Prediction {

    public static double predict(Team team1, Team team2){
        List<Team> result = SaveGA.caricaTeam("squadra.dat");
        Integer max1=0;
        for(Team team : result) {
            if (distance(team1, team) > max1)
                max1=distance(team1, team);
        }

        Integer max2=0;
        for(Team team : result) {
            if (distance(team2, team) > max2)
                max2=distance(team2, team);
        }


        return normalizeFitness( max1- max2,-Math.max(max1,max2),Math.max(max1,max2),0,100);
    }

    public static  double normalizeFitness(double x, double minX, double maxX, double minY, double maxY){
        double normalizedFitness = (x - minX) / (maxX - minX) * (maxY - minY) + minY;
        return Math.max(0, Math.min(100, normalizedFitness));
    }


    //calcola la distanza tra due team
    private static Integer distance(Team team1, Team team2){
        int dim=Math.min(team1.getPokemons().length, team2.getPokemons().length) ;
        Integer sum=0;

        //calcolo della distanza
        for(int i=0; i< dim; i++){
            //se i pokemon sono uguali
            if(team1.getPokemon(i)!=null) {
                if (team2.getPokemon(i)==null || Objects.equals(team1.getPokemon(i).getNome(), team2.getPokemon(i).getNome()))
                    sum += 1;
                //se i pokemon hanno lo stesso tipo
                if (team2.getPokemon(i)==null || Objects.equals(team1.getPokemon(i).getTipo1(), team2.getPokemon(i).getTipo1()) ||
                        Objects.equals(team1.getPokemon(i).getTipo1(), team2.getPokemon(i).getTipo2()))
                    sum += 1;

                //se i pokemon hanno lo stesso tipo
                if (team2.getPokemon(i)==null || Objects.equals(team1.getPokemon(i).getTipo2(), team2.getPokemon(i).getTipo1()) ||
                        Objects.equals(team1.getPokemon(i).getTipo2(), team2.getPokemon(i).getTipo2()))
                    sum += 1;

                //se i pokemon hanno lo stesso totale
                if (team2.getPokemon(i)==null || team1.getPokemon(i).getTotale() == team2.getPokemon(i).getTotale())
                    sum += 1;


                //se i pokemon hanno le mosse uguali
                for (int j = 0; j < 4; j++)
                    if (team2.getPokemon(i)==null || team1.getPokemon(i).getMosse().get(j) != null)
                        for (int k = 0; k < 4; k++)
                            if (team2.getPokemon(i)==null || team2.getPokemon(i).getMosse().get(k) != null)
                                if (Objects.equals(team1.getPokemon(i).getMosse().get(j).getNome(), team2.getPokemon(i).getMosse().get(k).getNome()))
                                    sum += 1;

                //se i pokemon hanno le mosse dello stesso tipo
                for (int j = 0; j < 4; j++)
                    if (team2.getPokemon(i)==null || team1.getPokemon(i).getMosse().get(j) != null)
                        for (int k = 0; k < 4; k++)
                            if (team2.getPokemon(i)==null || team2.getPokemon(i).getMosse().get(k) != null)
                                if (team2.getPokemon(i)==null || Objects.equals(team1.getPokemon(i).getMosse().get(j).getTipo(), team2.getPokemon(i).getMosse().get(k).getTipo()))
                                    sum += 1;
            }
        }

        return sum;
    }
}
