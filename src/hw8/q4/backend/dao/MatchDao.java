package hw8.q4.backend.dao;


import hw8.q4.backend.utilities.DbConnection;

import java.sql.*;

public class MatchDao {




    String INSERT_A_NEW_MATCH = "INSERT INTO premire_league.`match` (id, team_home_id, team_away_id, `date`, stadium_id)"+
    "VALUES(?, ?, 0, ?, ?);";

    String PRINT_ALL_MATCHES_INFORMATION = "SELECT id, team_home_id, team_away_id, `date`, stadium_id FROM premire_league.`match`; " ;


    public void addANewMatch(Integer id, Integer homeTeamId, Integer awayTeamId, Date date,int stadiumId) throws SQLException {

        Connection connection = DbConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(INSERT_A_NEW_MATCH);

        ps.setInt(1,id);
        ps.setInt(2,homeTeamId);
        ps.setInt(3,awayTeamId);
        ps.setDate(4,date);
        ps.setInt(5,stadiumId);

        if(ps.execute())
            System.out.println("The match information added to the database successfully");
    }

    public void printMatchesInformation() throws SQLException {

        Connection connection = DbConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(PRINT_ALL_MATCHES_INFORMATION);

        while (rs.next()){
            int id = rs.getInt("id");
            int homeTeamId = rs.getInt("home_team_id");
            int awayTeamId = rs.getInt("away_team_id");
            Date date = rs.getDate("date");
            int stadiumId = rs.getInt("stadium_id");

            System.out.println("======================================================================");
            System.out.println("The information for this player is: ");
            System.out.println(" id = " + id + " | " + " home_team_id = " + homeTeamId + " | " +
                    " away_team_id = " + awayTeamId + " date = " + date + " | " + " stadium_id = " + stadiumId );
            System.out.println("======================================================================");
        }
    }








}
