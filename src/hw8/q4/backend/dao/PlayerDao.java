package hw8.q4.backend.dao;

import hw8.q4.backend.entities.PlayerPosition;
import hw8.q4.backend.utilities.DbConnection;

import java.sql.*;
import java.util.Random;

public class PlayerDao {

    String INSERT_A_PLAYER = "INSERT INTO premire_league.player" +
            "(id,first_name, last_name, age, `position`, team_id, contract, salary)" +
            "VALUES(?,?, ?, ?, ?, ?,?,?);";

    String UPDATE_PLAYER_INFORMATION = " UPDATE premire_league.player  " +
            "SET id=?, first_name=? , last_name=? , age=? , position=?, contract=?  ,salary=? WHERE team_id=?  ;";


    String DELETE_A_PLAYER_BY_ID = "DELETE FROM premire_league.player WHERE id=?;";

    String DELETE_A_PLAYER_BY_TEAM_ID = "DELETE FROM premire_league.player WHERE team_id=?;";


    String FIND_HIGHEST_CONTRACT_FOR_PLAYERS = "select  first_name, last_name,salary, MAX(contract) as 'contract' from premire_league.player";

    String FIND_A_PLAYER_BY_ID = "SELECT first_name, last_name, age, `position`, team_id, contract, salary" +
            "FROM premire_league.player WHERE id=?;";



    private Long contract;

    public Long getContract() {
        return contract;
    }

    public void addAPlayer(Integer id, String firstName, String lastName, Integer age, Integer teamId,
                           PlayerPosition position, Long contract, Long salary) throws SQLException {

        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_A_PLAYER);

        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, lastName);
        preparedStatement.setDouble(4, age);
        preparedStatement.setString(5, String.valueOf(position));
        preparedStatement.setInt(6, teamId);
        preparedStatement.setDouble(7, contract);
        preparedStatement.setDouble(8, salary);

        if(preparedStatement.execute())
            System.out.println("The player added to the team successfully.");

    }


    public void CreatingPlayers(PlayerPosition position, int number) throws SQLException {

        for (int i = 1; i < number; i++) {

            Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_A_PLAYER);

            int id = idGenerator();
            String firstName = getAlphaNumericString(5);
            String lastName = getAlphaNumericString(7);
            double age = 18 * Math.random() + 22;
            long contract = contractGenerator();
            double salary = salaryGenerator();

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setDouble(4, age);
            preparedStatement.setString(5, String.valueOf(position));
            preparedStatement.setInt(6, i);
            preparedStatement.setDouble(7, contract);
            preparedStatement.setDouble(8, salary);

            preparedStatement.execute();
        }
    }

    public void UpdatePlayerInformation(int id,int teamId,String firstName,String lastName,int age,PlayerPosition position,
                                        long contract,long salary) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PLAYER_INFORMATION);

        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,firstName);
        preparedStatement.setString(3,lastName);
        preparedStatement.setInt(4,age);
        preparedStatement.setString(5,String.valueOf(position));
        preparedStatement.setLong(6,contract);
        preparedStatement.setLong(7,salary);
        preparedStatement.setInt(8,teamId);

        if (preparedStatement.executeUpdate() > 0)
            System.out.println("The item updated successfully!");

    }


    public void DeleteAPlayerById(int id) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_A_PLAYER_BY_ID);

        preparedStatement.setInt(1, id);

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("The item deleted successfully!");
        }
    }

    public void DeleteAPlayerByTeamId(int teamId) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_A_PLAYER_BY_TEAM_ID);

        preparedStatement.setInt(1, teamId);

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("The item deleted successfully!");
        }
    }

    public void MostPaidPlayer() throws SQLException {

        Connection connection = DbConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(FIND_HIGHEST_CONTRACT_FOR_PLAYERS);

        while (rs.next()) {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            long contract = rs.getLong("contract");
            long salary = rs.getLong("salary");

            System.out.println("======================================================================");
            System.out.println("The most Expensive player is: " + lastName);
            System.out.println(" first_name = " + firstName + " | " + " contract_value = " + contract + " | " + " salary =  " + salary);
            System.out.println("======================================================================");
        }

    }


    static String getAlphaNumericString(int n) {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    static long contractGenerator() {
        long lowerLimit = 1000000000L;
        long upperLimit = 9999999999L;
        Random r = new Random();
        return lowerLimit + ((long) (r.nextDouble() * (upperLimit - lowerLimit)));
    }

    static double salaryGenerator() {
        double lowerLimit = 5000000;
        double upperLimit = 10000000;
        Random r = new Random();
        return lowerLimit + ((long) (r.nextDouble() * (upperLimit - lowerLimit)));
    }

    static int idGenerator() {
        int lowerLimit = 1000;
        int upperLimit = 10000;
        Random r = new Random();
        return lowerLimit + ((int) (r.nextDouble() * (upperLimit - lowerLimit)));
    }
}
