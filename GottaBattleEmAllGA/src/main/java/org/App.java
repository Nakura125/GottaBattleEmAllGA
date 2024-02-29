package org;



import org.distance.Prediction;
import org.entity.Pokemon;
import org.entity.Team;
import org.ga.problem.PokemonProblem;

import org.repository.PokemonRepository;


import java.util.*;

import static org.distance.Prediction.predict;


/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        HashMap<Integer, Pokemon> pokemons = new PokemonRepository().getPokemons();
        String[] menuItems = {
                "Inserisci pokemon team 1",
                "Inserisci pokemon team 2",
                "Guarda i pokemon disponibili",
                "Genera un team casuale",
                "Predici il vincitore fra i due team",
                "Cancella Team",
                "Esci"
        };
        Team team1 = new Team();
        Team team2 = new Team();
        while (true) {

            printMenu(menuItems,team1,team2);
            int choice = new Scanner(System.in).nextInt();
            switch (choice) {
                case 1:
                    inserisciTeam(team1);
                    break;
                case 2:
                    inserisciTeam(team2);
                    break;
                case 3:
                    printPokemonPaged();
                    break;
                case 4:
                    generaTeamCasuale(team1, team2);
                    break;
                case 5:
                    System.out.println("La percentale di vittoria è: "+predict(team1,team2));
                    System.exit(0);
                    break;
                case 6:
                    team1 = new Team();
                    team2 = new Team();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Scelta non valida");
            }
        }

        // inizializzazione due team a caso
//        Team team1 = new Team();
//        Team team2 = new Team();
//        for(int i=0; i<6; i++){
//            team1.addPokemon(pokemons.get(i));
//            team2.addPokemon(pokemons.get(i*6+120));
//        }
//        System.out.println("Team1: ");
//        for (int i = 0; i < 6; i++) {
//            if (team1.getPokemon(i) != null)
//                System.out.println("\t"+team1.getPokemon(i).getNome());
//        }
//
//        System.out.println("Team2: ");
//        for (int i = 0; i < 6; i++) {
//            if (team2.getPokemon(i) != null)
//                System.out.println("\t"+team2.getPokemon(i).getNome());
//        }
//
//        //calcolo della distanza tra i due team
//        System.out.println("Predizione per team1: " + predict(team1, team2));
////        System.out.println("Predizione per team2: " + Prediction.predict(team2, team1));


    }



    public static void printMenu(String[] menuItems,Team team1, Team team2) {
        System.out.println("\n" +
                "  _____     __  __       ___       __  __  __    ____      ___   ____________ \n" +
                " / ___/__  / /_/ /____ _/ _ )___ _/ /_/ /_/ /__ / __/_ _  / _ | / / / ___/ _ |\n" +
                "/ (_ / _ \\/ __/ __/ _ `/ _  / _ `/ __/ __/ / -_) _//  ' \\/ __ |/ / / (_ / __ |\n" +
                "\\___/\\___/\\__/\\__/\\_,_/____/\\_,_/\\__/\\__/_/\\__/___/_/_/_/_/ |_/_/_/\\___/_/ |_|\n" +
                "                                                                              \n");
        System.out.println("----------------------------Menu---------------------------------");

        System.out.println("Seleziona un'opzione:");
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println(i+1 + ": " + menuItems[i]);

        }

        System.out.println("Team1: ");
        for (int i = 0; i < 6; i++) {
            if (team1.getPokemon(i) != null){
                System.out.print("\t" + team1.getPokemon(i).getNome()+ "(");
                System.out.print(" Totale:" + team1.getPokemon(i).getTotale());
                System.out.print(" Tipo1:" + team1.getPokemon(i).getTipo1());
                System.out.print(" Tipo2: " + team1.getPokemon(i).getTipo2());
                if (team1.getPokemon(i).getMosse().get(0) != null) {
                    System.out.print(" Mossa1: " + team1.getPokemon(i).getMosse().get(0).getNome());
                    System.out.print(" MossaTipo:" + team1.getPokemon(i).getMosse().get(0).getTipo());
                }
                if (team1.getPokemon(i).getMosse().get(1) != null) {
                    System.out.print(" Mossa2: " + team1.getPokemon(i).getMosse().get(1).getNome());
                    System.out.print(" MossaTipo: " + team1.getPokemon(i).getMosse().get(1).getTipo());
                }
                if (team1.getPokemon(i).getMosse().get(2) != null) {
                    System.out.print(" Mossa3: " + team1.getPokemon(i).getMosse().get(2).getNome());
                    System.out.print(" MossaTipo: " + team1.getPokemon(i).getMosse().get(2).getTipo());
                }
                if (team1.getPokemon(i).getMosse().get(3) != null) {
                    System.out.print(" Mossa4: " + team1.getPokemon(i).getMosse().get(3).getNome());
                    System.out.print(" MossaTipo: " + team1.getPokemon(i).getMosse().get(3).getTipo());
                }

                System.out.println(")");
            }

        }

        System.out.println("Team2: ");
        for (int i = 0; i < 6; i++) {
            if (team2.getPokemon(i) != null) {
                System.out.print("\t" + team2.getPokemon(i).getNome()+ "(");
                System.out.print(" Totale:" + team2.getPokemon(i).getTotale());
                System.out.print(" Tipo1:" + team2.getPokemon(i).getTipo1());
                System.out.print(" Tipo2" + team2.getPokemon(i).getTipo2());
                if (team2.getPokemon(i).getMosse().get(0) != null) {
                    System.out.print(" Mossa1:" + team2.getPokemon(i).getMosse().get(0).getNome());
                    System.out.print(" MossaTipo:" + team2.getPokemon(i).getMosse().get(0).getTipo());
                }
                if (team2.getPokemon(i).getMosse().get(1) != null) {
                    System.out.print(" Mossa2: " + team2.getPokemon(i).getMosse().get(1).getNome());
                    System.out.print(" MossaTipo:" + team2.getPokemon(i).getMosse().get(1).getTipo());
                }
                if (team2.getPokemon(i).getMosse().get(2) != null) {
                    System.out.print(" Mossa3: " + team2.getPokemon(i).getMosse().get(2).getNome());
                    System.out.print(" MossaTipo: " + team2.getPokemon(i).getMosse().get(2).getTipo());
                }
                if (team2.getPokemon(i).getMosse().get(3) != null) {
                    System.out.print(" Mossa4: " + team2.getPokemon(i).getMosse().get(3).getNome());
                    System.out.print(" MossaTipo: " + team2.getPokemon(i).getMosse().get(3).getTipo());
                }

                System.out.println(")");
            }
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public static void inserisciTeam(Team team){
        System.out.println("Inserisci il nome del pokemon");
        String nome = new Scanner(System.in).nextLine();

        if (team.isFull()){
            System.out.println("Team pieno");
            return;
        }


        Pokemon pokemon = new PokemonRepository().getPokemons().values().stream().map(p -> p.getNome().equals(nome) ? p : null).filter(Objects::nonNull).findFirst().orElse(null);
        if(pokemon == null){
            System.out.println("Pokemon non trovato");
            return;
        }
        team.addPokemon(pokemon);
    }

    public static void printPokemonPaged(){
        List<Pokemon> pokemons = new ArrayList<>(new PokemonRepository().getPokemons().values());
        int page = 0;
        int pageSize = 6;
        while (true){
            for (int i = page*pageSize; i < (page+1)*pageSize; i++) {
                if(i >= pokemons.size()){
                    break;
                }
                System.out.println(pokemons.get(i).getNome());
            }
            System.out.println("Premi 1 per andare avanti, 0 per andare indietro, -1 per uscire");
            String input = new Scanner(System.in).nextLine();
            if(input.equals("1")){
                page++;
            }else if(input.equals("0")){
                page--;
            }else if(input.equals("-1")){
                break;
            }
        }
    }

    public static void generaTeamCasuale(Team team1, Team team2){
        PokemonRepository pokemonRepository = new PokemonRepository();
        List<Pokemon> pokemons = new ArrayList<>(pokemonRepository.getPokemons().values());
        Collections.shuffle(pokemons);
        for (int i = 0; i < 6; i++) {
            team1.addPokemon(pokemons.get(i));
            team2.addPokemon(pokemons.get(i+6));
        }
    }
}


