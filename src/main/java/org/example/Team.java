package org.example;
import java.util.ArrayList;
import java.util.List;

public class Team {
    public int id;
    public String name;
    public List<Player> players = new ArrayList<>();

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Question 3
    public Integer getPlayersGoals() {
        int total = 0;
        for (Player p : players) {
            if (p.goalNb == null) {

                throw new RuntimeException("Calcul impossible : le joueur " + p.name + " a un nombre de buts inconnu.");
            }
            total += p.goalNb;
        }
        return total;
    }
}
