package hw8.q4.frontend;

import hw8.q4.backend.dao.MatchDao;
import hw8.q4.backend.entities.PlayerPosition;
import hw8.q4.backend.exceptions.ManagerException;
import hw8.q4.backend.manager.LeagueManager;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UIManager {

    private LeagueManager leagueManager;
    private Scanner input;
    private static int select;
    private MatchDao matchDao;

    public UIManager() throws SQLException {
        leagueManager = new LeagueManager();
        input = new Scanner(System.in);

    }

    public void showMenu() {
        System.out.println();
        System.out.println(">> Please choose A number from list bellow : ");
        System.out.println();
        System.out.println("1) Add a new coach");
        System.out.println("2) Transfer a coach");
        System.out.println("3) Delete a coach");
        System.out.println("4) print the most expensive Coach");
        System.out.println("5) Add a new random player");
        System.out.println("6) Add a new player");
        System.out.println("7) Transfer a player");
        System.out.println("8) print the mostpaid player");
        System.out.println("9) Delete a player");
        System.out.println("10) print players performance in matches");
        System.out.println("11) print the list of cities and the number of their teams");
        System.out.println("12) print the ranking table");
        System.out.println("13) print the league champion");
        System.out.println("14) print the list of derbies ");
        System.out.println("15) print the list of derbies with the most scored goals");
        System.out.println("16) exit");
        System.out.println();

    }

    public void Run() throws SQLException, ManagerException {
        System.out.println("====================================================================================");
        System.out.println("===========================WELCOME TO PREMIERE LEAGUE APPLICATION===================");
        System.out.println("====================================================================================");

        while (true) {


            showMenu();
            try {
                select = input.nextInt();
            } catch (InputMismatchException exception) {
                exception.printStackTrace();
                System.out.println(exception.getMessage());
            }

            switch (select) {
                case 1:
                    System.out.println("Enter the Id");
                    int id = input.nextInt();
                    System.out.println("Enter The firstname");
                    String firstName = input.next();
                    System.out.println("Enter the lastname");
                    String lastName = input.next();
                    System.out.println("Enter the contract value");
                    Long contract = input.nextLong();
                    System.out.println("Enter the teamId");
                    int teamId = input.nextInt();

                    leagueManager.AddNewCoach(id, firstName, lastName, contract, teamId);
                    break;

                case 2:
                    System.out.println("Enter the Id");
                    int coachId = input.nextInt();
                    System.out.println("Enter The firstname");
                    firstName = input.next();
                    System.out.println("Enter the lastname");
                    lastName = input.next();
                    System.out.println("Enter the firstTeamId");
                    int firstTeamId = input.nextInt();
                    System.out.println("Enter the second TeamId");
                    int secondTeamId = input.nextInt();
                    System.out.println("Enter the contract value");
                    contract = input.nextLong();
                    System.out.println("Enter the Month");
                    int month = input.nextInt();
                    System.out.println("Enter the day");
                    int day = input.nextInt();

                    leagueManager.CoachesTransfer(coachId, firstName, lastName, firstTeamId, secondTeamId, contract, month, day);
                    break;

                case 3:
                    System.out.println("Please enter the coach id: ");
                    coachId = input.nextInt();
                    leagueManager.DeleteACoachById(coachId);
                    break;

                case 4:
                    leagueManager.PrintMostPaidCoach();
                    break;

                case 5:
                    System.out.println("Enter the player position");
                    PlayerPosition position = PlayerPosition.valueOf(input.next());
                    System.out.println("Enter the number of player");
                    int number = input.nextInt();
                    leagueManager.AddNewRandomPlayer(position, number);
                    break;

                case 6:
                    System.out.println("Enter the Id");
                    int playerId = input.nextInt();
                    System.out.println("Enter The firstname");
                    firstName = input.next();
                    System.out.println("Enter the lastname");
                    lastName = input.next();
                    System.out.println("Enter age");
                    int age = input.nextInt();
                    System.out.println("Enter the player position");
                    PlayerPosition playerPosition = PlayerPosition.valueOf(input.next());
                    System.out.println("Enter the team_id");
                    teamId = input.nextInt();
                    System.out.println("Enter the contract value");
                    contract = input.nextLong();
                    System.out.println("Enter the salary value");
                    Long salary = input.nextLong();
                    leagueManager.AddANewPlayer(playerId,firstName,lastName,age,playerPosition,teamId,contract,salary);
                    break;


                case 7:
                    System.out.println("Enter the Id");
                    int playerid = input.nextInt();
                    System.out.println("Enter The firstname");
                    firstName = input.next();
                    System.out.println("Enter the lastname");
                    lastName = input.next();
                    System.out.println("Enter the age");
                     age = input.nextInt();
                    System.out.println("Enter the firstTeamId");
                    firstTeamId = input.nextInt();
                    System.out.println("Enter the second TeamId");
                    secondTeamId = input.nextInt();
                    System.out.println("Enter the player position");
                     playerPosition = PlayerPosition.valueOf(input.next());
                    System.out.println("Enter the contract value");
                    contract = input.nextLong();
                    System.out.println("Enter the salary value");
                     salary = input.nextLong();
                    System.out.println("Enter the Month");
                    month = input.nextInt();
                    System.out.println("Enter the day");
                    day = input.nextInt();

                    leagueManager.PlayersTransfer(playerid, firstName, lastName, age, firstTeamId, playerPosition, secondTeamId,
                            contract, salary, month, day);
                    break;

                case 8:
                    leagueManager.PrintMostPaidPlayer();
                    break;

                case 9:
                    System.out.println("Enter the id");
                    id = input.nextInt();
                    leagueManager.DeletePlayer(id);
                    break;

                case 10:
                    leagueManager.PrintPlayersPerformanceInMatches();
                    break;


                case 11:
                    leagueManager.PrintListOfTeamInEachCity();
                    break;

                case 12:
                    leagueManager.PrintFinalRankingTable();
                    break;

                case 13:
                    leagueManager.PrintLeagueChampion();
                    break;

                case 14:
                    leagueManager.PrintDerbyMatches();
                    break;

                case 15:
                    leagueManager.PrintMostScoredGoalsDerby();
                    break;

                case 16:
                    System.out.println("Exiting the application...");
                    System.exit(0);
            }
        }

    }
}
