package org.ga;

import org.entity.Team;

import java.io.*;
import java.util.List;
public class SaveGA {

    public static void main(String[] args) {
        List<Team> teams= ResultGA.getResult();

        // Salvataggio del team
        salvaTeam(teams, "squadra.dat");
    }

    public static void salvaTeam(List<Team> team, String nomeFile) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeFile))) {
            out.writeObject(team);
            System.out.println("Team salvato con successo nel file: " + nomeFile);
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio del team: " + e.getMessage());
        }
    }

    public static List<Team> caricaTeam(String nomeFile) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeFile))) {
            List<Team> teams = (List<Team>) in.readObject();
            System.out.println("Team caricato con successo dal file: " + nomeFile);
            return teams;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Errore durante il caricamento del team: " + e.getMessage());
            return null;
        }
    }
}
