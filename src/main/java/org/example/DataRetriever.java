
package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DataRetriever {

    /**
     * Question 2 & 4.1 : Récupérer une équipe et tous ses joueurs avec leurs buts.
     */
    public Team findTeamById(Integer id) {
        String sqlTeam = "SELECT * FROM Team WHERE id = ?";
        String sqlPlayers = "SELECT * FROM Player WHERE id_team = ?";

        try (Connection conn = DBConnection.getDBConnection()) {

            PreparedStatement psTeam = conn.prepareStatement(sqlTeam);
            psTeam.setInt(1, id);
            ResultSet rsTeam = psTeam.executeQuery();

            if (rsTeam.next()) {
                Team team = new Team(
                        rsTeam.getInt("id"),
                        rsTeam.getString("name")
                );

                PreparedStatement psPlayers = conn.prepareStatement(sqlPlayers);
                psPlayers.setInt(1, id);
                ResultSet rsPlayers = psPlayers.executeQuery();

                while (rsPlayers.next()) {
                    Integer goals = (Integer) rsPlayers.getObject("goal_nb");

                    Player p = new Player(
                            rsPlayers.getInt("id"),
                            rsPlayers.getString("name"),
                            rsPlayers.getInt("age"),
                            PlayerPositionEnum.valueOf(rsPlayers.getString("position")),
                            team,
                            goals
                    );
                    team.players.add(p);
                }
                return team;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération : " + e.getMessage());
        }
        return null;
    }

    /**
     * Question 4.2 : Sauvegarder une équipe.
     * Si l'ID existe, on met à jour. S'il n'existe pas, on crée.
     */
    public Team saveTeam(Team teamToSave) {
        String sqlCheck = "SELECT id FROM Team WHERE id = ?";
        String sqlInsert = "INSERT INTO Team (id, name) VALUES (?, ?)";
        String sqlUpdate = "UPDATE Team SET name = ? WHERE id = ?";

        try (Connection conn = DBConnection.getDBConnection()) {
            PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
            psCheck.setInt(1, teamToSave.id);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
                psUpdate.setString(1, teamToSave.name);
                psUpdate.setInt(2, teamToSave.id);
                psUpdate.executeUpdate();
                System.out.println("Équipe mise à jour avec succès.");
            } else {
                PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
                psInsert.setInt(1, teamToSave.id);
                psInsert.setString(2, teamToSave.name);
                psInsert.executeUpdate();
                System.out.println("Nouvelle équipe créée avec succès.");
            }
            return teamToSave;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
        return null;
    }
}