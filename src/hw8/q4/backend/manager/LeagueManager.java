package hw8.q4.backend.manager;

import hw8.q4.backend.dao.*;
import hw8.q4.backend.entities.*;
import hw8.q4.backend.exceptions.ManagerException;


import java.sql.SQLException;

public class LeagueManager {

    private CoachDao coachDao;
    private MatchDao matchDao;
    private PlayerDao playerDao;
    private StadiumDao stadiumDao;
    private TeamDao teamDao;
    private CoachTransferDao coachTransferDao;
    private PlayerTransferDao playerTransferDao;
    private TeamMtachPerformanceDao teamMtachPerformanceDao;
    private String[] playerPositions = {"GK", "LB", "RB", "LB", "CB", "DMF", "RMF", "LMF",
            "RWF", "LWF", "SS", "CF"};

    public LeagueManager() {
        this.coachDao = new CoachDao();
        this.matchDao = new MatchDao();
        this.playerDao = new PlayerDao();
        this.stadiumDao = new StadiumDao();
        this.teamDao = new TeamDao();
        this.coachTransferDao = new CoachTransferDao();
        this.playerTransferDao = new PlayerTransferDao();
        this.teamMtachPerformanceDao = new TeamMtachPerformanceDao();
    }


    public void AddNewCoach(Integer id, String firstName, String lastName, Long contract, Integer teamId) throws SQLException, ManagerException {

        if (contract > 12000000000L)
            throw new ManagerException("Too much contract value");
        if (teamId < 0 || teamId > 30)
            throw new ManagerException("Invalid input teamId");

        Coach coach = new Coach(id, firstName, lastName, contract, teamId);
        try {
            coachDao.AddCoach(id, firstName, lastName, teamId, contract);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void DeleteACoachById(int id) throws SQLException {
        coachDao.DeleteACoachById(id);
    }

    public void CoachesTransfer(Integer coachId, String firstName, String lastName, Integer firstTeamId, Integer secondTeamId, Long contractValue,
                                int month, int day) throws SQLException, ManagerException {
        if ((month != 8) && !(day > 15 && day < 30))
            throw new ManagerException("IllegalTransferDate");

        CoachTransfer coachTransfer = new CoachTransfer(coachId, firstTeamId, secondTeamId, contractValue);
        try {
            coachTransferDao.transferACoach(coachId, firstTeamId, secondTeamId, contractValue);
            DeleteACoachById(firstTeamId);
            coachDao.UpdateCoachInformation(coachId,firstName,lastName,secondTeamId,contractValue);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void PrintMostPaidCoach() throws SQLException {
            coachDao.MostPaidCoach();
    }

    public void AddANewPlayer(Integer id, String firstName, String lastName, int age, PlayerPosition position, Integer teamId,
                              Long contract, Long salary) throws ManagerException, SQLException {
        if (id > 100)
            throw new ManagerException("InvalidInputId");
        if (age > 40)
            throw new ManagerException("InvalidInputAge");
        if (contract > 6000000000L || salary > 5000000)
            throw new ManagerException("InvalidContractOrSalary");
        if (!PositionChecker(position))
            throw new ManagerException("InvalidInputPosition");

        playerDao.addAPlayer(id, firstName, lastName, age, teamId, position, contract, salary);
    }


    public void AddNewRandomPlayer(PlayerPosition position, int number) {
        String pos = String.valueOf(position);
        for (String playerPosition : playerPositions) {
            if (playerPosition.equals(pos)) {
                Player player = new Player(null, null, null, null, null, null,
                        position, null);
                try {
                    playerDao.CreatingPlayers(position, number);
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }


    public void PlayersTransfer(Integer playerId, String firstName, String lastName, Integer age, Integer firstTeamId,
                                PlayerPosition position, Integer secondTeamId, Long contract, Long salary,
                                int month, int day) throws SQLException, ManagerException {

        if ((month != 8) && !(day > 15 && day < 30))
            throw new ManagerException("IllegalTransferDate");

        PlayerTransfer playerTransfer = new PlayerTransfer(playerId, firstTeamId, secondTeamId, contract, salary);
        try {
            playerTransferDao.transferAPlayer(playerId, firstTeamId, secondTeamId, contract, salary);
            DeletePlayerByTeamId(firstTeamId);
            playerDao.addAPlayer(playerId,firstName,lastName,age,secondTeamId,position,contract,salary);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void DeletePlayer(int id) throws SQLException {
        playerDao.DeleteAPlayerById(id);
    }

    public void DeletePlayerByTeamId(int teamId) throws SQLException {
        playerDao.DeleteAPlayerByTeamId(teamId);
    }

    public void PrintMostPaidPlayer() throws SQLException {
            playerDao.MostPaidPlayer();
    }

    public void PrintListOfTeamInEachCity() throws SQLException {
        teamDao.ShowListOfTeamsForEachCities();
    }

    public void PrintFinalRankingTable() throws SQLException {
        teamMtachPerformanceDao.PrintRankingTable();
    }

    public void PrintLeagueChampion() throws SQLException {
        teamMtachPerformanceDao.FindChampion();
    }

    public void PrintDerbyMatches() throws SQLException {
        teamMtachPerformanceDao.DerbyMatchesGoals();
    }

    public void PrintMostScoredGoalsDerby() throws SQLException {
        teamMtachPerformanceDao.MostGoalDerbyMatch();
    }

    private boolean PositionChecker(PlayerPosition position) {
        String playerPosition = String.valueOf(position);
        for (String ps : playerPositions) {
            if (ps.equals(playerPosition))
                return true;
        }
        return false;
    }


}
