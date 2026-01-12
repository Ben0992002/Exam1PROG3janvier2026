package org.example;

public class Main {
    public static void main(String[] args) {
        DataRetriever dr = new DataRetriever();

        // --- Question 4.1 : Test de findTeamById et getPlayersGoals ---
        System.out.println("--- Test Récupération Équipe ---");
        // On teste avec l'ID de Real Madrid
        Team team1 = dr.findTeamById(1);
        if (team1 != null) {
            try {
                // Si tous les joueurs ont un score
                System.out.println("Équipe : " + team1.name + " | Total buts : " + team1.getPlayersGoals());
            } catch (RuntimeException e) {
                // Si l'un des joueurs est à 'nulle'
                System.out.println("Erreur attendue : " + e.getMessage());
            }
        }

        // --- Question 4.2 : Test de saveTeam (Création et Mise à jour) ---
        System.out.println("\n--- Test Sauvegarde Équipe ---");

        // Cas A : Création (si l'ID n'existe pas encore)
        Team newTeam = new Team(3, "FC Porto");
        Team savedTeam = dr.saveTeam(newTeam);

        // Cas B : Mise à jour (on change le nom de la même équipe)
        if (savedTeam != null) {
            savedTeam.name = "FC Porto Officiel";
            dr.saveTeam(savedTeam);
        }
    }
}