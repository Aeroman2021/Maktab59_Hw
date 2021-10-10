package hw8.q4.backend.dao;

import hw8.q4.backend.entities.PlayerPosition;
import hw8.q4.backend.utilities.DbConnection;

import java.sql.*;

public class PlayerMatchPerformance {
    String INSERT_PLAYER_INFORMATION = "INSERT INTO player_match_performance" +
            "(player_id, match_id,team_id, scored_goals)" +
            " VALUES(?, ?, ?,?);";

    String READ_PLAYER_INFORMATION_IN_MATCH = "SELECT player_id, match_id,team_id scored_goals" +
            "FROM player_match_performance;";

    public void AddPlayerPerformanceInMatch(int playerId, int matchId, int scoredGoals) throws SQLException {

        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PLAYER_INFORMATION);

        preparedStatement.setInt(1, playerId);
        preparedStatement.setInt(2, matchId);
        preparedStatement.setInt(3, scoredGoals);

        if (preparedStatement.execute())
            System.out.println("The record added to the database successfully.");

    }

    public void ReadPlayerPerformanceInMatch() throws SQLException {

        Connection connection = DbConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(READ_PLAYER_INFORMATION_IN_MATCH);

        System.out.println("======================================================================");
        while (rs.next()) {
            int playerId = rs.getInt("player_id");
            int matchId = rs.getInt("match_id");
            int teamId = rs.getInt("team_id");
            int scoredGoals = rs.getInt("scored_goals");
            System.out.println("player_id = " + playerId + " | " + " match_id = " + matchId + " | " + " team_id = " + teamId + " | " + " scored_goals =  " + scoredGoals);
        }
        System.out.println("======================================================================");
    }


}
