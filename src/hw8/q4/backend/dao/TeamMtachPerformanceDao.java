package hw8.q4.backend.dao;

import hw8.q4.backend.utilities.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TeamMtachPerformanceDao {

    private final String TABLE_RANKING = "SELECT team_id," +
            " premire_league.team.name," +
            " SUM(point)                              AS 'point'," +
            " SUM(scored_golas) - SUM(recieved_goals) AS goal_difference" +
            " FROM premire_league.team_match_performance" +
            " JOIN premire_league.team" +
            " ON premire_league.team_match_performance.team_id = premire_league.team.id" +
            " GROUP BY team_id" +
            " ORDER BY `point` DESC";

    private final String DERBY_MATCH_GOALS_SCORED = "select premire_league.`match`.id as 'match_id'," +
            " team.name as 'home_team'," +
            " t1.name as 'away_team'," +
            " SUM(scored_golas + recieved_goals) as 'number_of_goals'" +
            " from premire_league.`match` " +
            " join premire_league.team_match_performance  on premire_league.`match`.id = premire_league.team_match_performance.match_id" +
            " join team  on premire_league.`match`.team_home_id = team.id" +
            " join team t1 on premire_league.`match`.team_away_id = t1.id" +
            " where premire_league.`match`.id = 5 and tmp.team_id = 1" +
            " UNION " +
            " select premire_league.`match`.id as 'match_id'," +
            " team.name as 'home_team'," +
            " t1.name as 'away_team'," +
            " SUM(scored_golas + recieved_goals) as 'number_of_goals'" +
            " from premire_league.`match` " +
            " join premire_league.team_match_performance  on premire_league.`match`.id = premire_league.team_match_performance.match_id" +
            " join team  on premire_league.`match`.team_home_id = team.id" +
            " join team t1 on premire_league.`match`.team_away_id = t1.id" +
            " where premire_league.`match`.id = 11" +
            " and tmp.team_id = 4"+
           " order by number_of_goals DESC";

    public void PrintRankingTable() throws SQLException {
        Connection connection = DbConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(TABLE_RANKING);

        System.out.println("====================================================================================");
        System.out.println("==============================Final Ranking Table===================================");
        System.out.println("====================================================================================");
        while (rs.next()) {
            int teamId = rs.getInt("team_id");
            String teamName = rs.getString("name");
            int point = rs.getInt("point");
            int gd = rs.getInt("goal_difference");

            System.out.println(" team_id = " + teamId + " | " + " teamName = " + teamName + " | " +
                    " point = " + point + " | " + " goal_difference = " + gd);
        }
        System.out.println("=====================================================================================");
    }

    public void FindChampion() throws SQLException {
        Connection connection = DbConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(TABLE_RANKING);

        System.out.println("====================================================================================");
        System.out.println("==============================The League Champion===================================");
        System.out.println("====================================================================================");
        if (rs.next()) {
            String teamName = rs.getString("name");
            int point = rs.getInt("point");
            int gd = rs.getInt("goal_difference");
            System.out.println(teamName + " | " + " point = " + point + " | " + " goal_difference = " + gd);
        }
        System.out.println("=====================================================================================");
    }

    public void DerbyMatchesGoals() throws SQLException {
        Connection connection = DbConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(DERBY_MATCH_GOALS_SCORED);

        System.out.println("====================================================================================");
        System.out.println("==============================All Derbies Goals=====================================");
        System.out.println("====================================================================================");
        while (rs.next()) {
            int matchId = rs.getInt("match_id");
            String homeTeamName = rs.getString("home_team");
            String awayTeamName = rs.getString("away_team");
            int numberOfGoals = rs.getInt("number_of_goals");

            System.out.println(" match_id = " + matchId + " -> "  + homeTeamName + " VS " + awayTeamName);
            System.out.println("number of goals : " + numberOfGoals);
        }
        System.out.println("=====================================================================================");
    }


    public void MostGoalDerbyMatch() throws SQLException {
        Connection connection = DbConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(DERBY_MATCH_GOALS_SCORED);

        System.out.println("====================================================================================");
        System.out.println("==============================Derby with the most scored goal=======================");
        System.out.println("====================================================================================");
        if (rs.next()) {
            int matchId = rs.getInt("match_id");
            String homeTeamName = rs.getString("home_team");
            String awayTeamName = rs.getString("away_team");
            int numberOfGoals = rs.getInt("number_of_goals");

            System.out.println(" match_id = " + matchId + " -> "  + homeTeamName + " VS " + awayTeamName);
            System.out.println("number of goals : " + numberOfGoals);
        }
        System.out.println("=====================================================================================");
    }




}