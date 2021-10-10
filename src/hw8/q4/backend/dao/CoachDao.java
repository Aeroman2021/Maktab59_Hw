package hw8.q4.backend.dao;

import hw8.q4.backend.utilities.DbConnection;


import java.sql.*;

public class CoachDao {

    String INSERT_COACH = "INSERT INTO premire_league.coach (id, first_name, last_name, team_id, contract) " +
            "VALUES(?, ?, ?,?,?);";
    String SELECT_A_COACH_BY_ID = "SELECT id,first_name, last_name, salary,team_id, contract FROM premire_league.coach" +
            "WHERE id=?";

    String UPDATE_COACH_INFORMATION = " UPDATE premire_league.coach  " +
            "SET id=?, first_name=? , last_name=? , contract=? WHERE team_id=?  ;";

    String DELETE_A_COACH_BY_TEAM_ID = "DELETE FROM premire_league.coach WHERE team_id=?";

    String FIND_HIGHEST_CONTRACT_FOR_COACHES = "select  first_name, last_name, MAX(contract) as'contract' from premire_league.coach";


    public void AddCoach(Integer id, String firstName, String lastName,
                         Integer teamId, Long contract) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COACH);

        preparedStatement.setDouble(1, id);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, lastName);
        preparedStatement.setDouble(4, teamId);
        preparedStatement.setDouble(5, contract);

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("The item added successfully!");
        }
    }

    public void SelectACoachById(int id) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_A_COACH_BY_ID);

        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();

        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        long contract = rs.getLong("contract");

        System.out.println("======================================================================");
        System.out.println("The  coach information for the input id");
        System.out.println(" firstName = " + firstName + " | " + " lastName = " + lastName +
                " contract = " + contract);
        System.out.println("======================================================================");
    }

    public void UpdateCoachInformation(int coachId,String firstName,String lastName,int teamId,long contract)
            throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COACH_INFORMATION);

        preparedStatement.setInt(1,coachId);
        preparedStatement.setString(2,firstName);
        preparedStatement.setString(3,lastName);
        preparedStatement.setLong(4,contract);
        preparedStatement.setInt(5,teamId);


        if (preparedStatement.executeUpdate() > 0)
            System.out.println("The item updated successfully!");

    }


    public void DeleteACoachById(int teamId) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_A_COACH_BY_TEAM_ID);

        preparedStatement.setInt(1, teamId);

        if (preparedStatement.executeUpdate() > 0)
            System.out.println("The Coach information deleted successfully!");

    }

    public void MostPaidCoach() throws SQLException {

        Connection connection = DbConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(FIND_HIGHEST_CONTRACT_FOR_COACHES);

        while (rs.next()) {
            String lastName = rs.getString("last_name");
            long contract = rs.getLong("contract");

            System.out.println("======================================================================");
            System.out.println("The most Expensive coach is: ");
            System.out.println(lastName + " | " + " contract_value = " + contract);
            System.out.println("======================================================================");
        }

    }

}
