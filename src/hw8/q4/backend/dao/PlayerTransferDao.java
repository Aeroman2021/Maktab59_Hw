package hw8.q4.backend.dao;

import hw8.q4.backend.utilities.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerTransferDao {
    String INSERT_PLAYER_TRANSFER_INFORMATION = "INSERT INTO premire_league.player_transfer " +
            "(player_id, first_team_id, second_team_id, contract, salary)  VALUES(?,?,?,?,?); ";

    String FIND_HIGHEST_CONTRACT_FOR_COACHES_TRANSFER = "SELECT player-id,max(contract_value) as 'contract' " +
            "from premire_league.player_transfer ";

    private Long contract;

    public Long getContract() {
        return contract;
    }

    public void transferAPlayer(Integer playerId, Integer firstTeamId, Integer secondTeamId, Long contractValue,
                                Long salary) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PLAYER_TRANSFER_INFORMATION);

        preparedStatement.setInt(1, playerId);
        preparedStatement.setInt(2, firstTeamId);
        preparedStatement.setInt(3, secondTeamId);
        preparedStatement.setLong(4, contractValue);
        preparedStatement.setLong(5, salary);

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("The item added successfully!");
        }
    }


}
