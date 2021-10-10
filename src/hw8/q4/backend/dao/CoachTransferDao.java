package hw8.q4.backend.dao;

import hw8.q4.backend.utilities.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoachTransferDao {

    String INSERT_COACH_TRANSFER_INFORMATION = "INSERT INTO premire_league.caoch_transfer " +
            "(coach_id, first_team_id, second_team_id, contract_value) VALUES(?, ?, ?,?); ";

    String FIND_HIGHEST_CONTRACT_FOR_COACHES_TRANSFER = "SELECT coach-id,max(contract_value) as 'contract' " +
            "from premire_league.coach_transfer ";

    public void transferACoach(Integer coachId, Integer firstTeamId, Integer secondTeamId, Long contractValue) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COACH_TRANSFER_INFORMATION);

        preparedStatement.setInt(1, coachId);
        preparedStatement.setInt(2, firstTeamId);
        preparedStatement.setInt(3, secondTeamId);
        preparedStatement.setLong(4, contractValue);

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("The transformation performed successfully!");
        }
    }

    public void highestContractForACoachTransfer() throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_HIGHEST_CONTRACT_FOR_COACHES_TRANSFER);
        ResultSet rs = preparedStatement.executeQuery();


        int coachId = rs.getInt("coach_id");
        int firstTeamId = rs.getInt("first_team_id");
        int second_team_id = rs.getInt("second_team_id");
        long contract = rs.getLong("contract_value");

        System.out.println("======================================================================");
        System.out.println("The Information for this coach is: ");
        System.out.println(" coach_id = " + coachId + " | " + " first_team_id = " + firstTeamId +
                " second_team_id = " + second_team_id + " | " + " contract_value = " + contract);
        System.out.println("======================================================================");
    }




}
