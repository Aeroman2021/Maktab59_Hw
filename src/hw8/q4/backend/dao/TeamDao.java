package hw8.q4.backend.dao;
import hw8.q4.backend.utilities.DbConnection;

import java.sql.*;

public class TeamDao {
    String SELECT_LIST_OF_TEAMS_FOR_CITIES = "SELECT premire_league.city.name as 'city' ," +
            "  count(city_id) as 'number_of_teams' " +
            "FROM  premire_league.team  JOIN  premire_league.city  " +
            " ON  premire_league.team.city_id = premire_league.city.id" +
            " GROUP BY city_id " +
            " ORDER BY count(city_id) DESC";

    public void ShowListOfTeamsForEachCities() throws SQLException {
        Connection connection = DbConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(SELECT_LIST_OF_TEAMS_FOR_CITIES);

        System.out.println("======================================================================");
        while (rs.next()) {
            int numberOfTeams = rs.getInt("number_of_teams");
            String city = rs.getString("city");
            System.out.println(city  + " -> " + numberOfTeams);
        }
        System.out.println("======================================================================");
    }


}
