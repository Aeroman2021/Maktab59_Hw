package hw8.q4.backend.db;

import hw8.q4.backend.exceptions.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

public class dbManager {

    private static class Statement {


        private static class Schema {
            private static String CREATE = "CREATE DATABASE IF NOT EXISTS premire_league";
        }

        private static class City {

            private static String CREATE_TABLE =

                    "CREATE TABLE `city` (" +
                            " `id` smallint(6) NOT null ," +
                            " `name` varchar(10) NOT NULL," +
                            " PRIMARY KEY (`id`))" +
                            " ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;";

        }

        private static class Coach {
            private static String CREATE_TABLE = "create table if not exists premire_league.coach(" +
                    "id int(3) not null  primary key,first_name mediumtext not null,last_name mediumtext not null," +
                    "team_id mediumtext not null, contract double not null);";

            private static String INSERT_CAOCH = "INSERT INTO premire_league.coach (id,first_name,last_name,team_id,contract) " +
                    "VALUES(?, ?,?,?,?);";
        }

        private static class CoachTransfer {

            private static String CREATE_TABLE =
                    " create table if not exists premire_league.caoch_transfer(" +
                            " id int auto_increment" +
                            " primary key," +
                            " coach_id smallint not null," +
                            " first_team_id smallint not null," +
                            " second_team_id smallint not null," +
                            " contract_value bigint not null," +
                            " constraint caoch_transfer_FK" +
                            " foreign key (first_team_id) references premire_league.team (id)" +
                            " on update cascade on delete cascade," +
                            " constraint caoch_transfer_FK_1" +
                            " foreign key (second_team_id) references premire_league.team (id)" +
                            " on update cascade on delete cascade);";


            private static String INSERT_COACH_TRANSFER = " INSERT INTO premire_league.caoch_transfer" +
                    " (coach_id, first_team_id, second_team_id, contract_value)" +
                    " VALUES(?,?,?,?);";


        }

        private static class Match {
            private static String CREATE_TABLE = "CREATE TABLE if not exist `match` (" +
                    "`id` smallint(6) NOT NULL," +
                    "`team_home_id` smallint(6) NOT NULL," +
                    "`team_away_id` smallint(6) NOT NULL," +
                    "`date` datetime NOT NULL," +
                    "   `stadium_id` smallint(6) NOT NULL," +
                    " PRIMARY KEY (`id`)," +
                    " KEY `match_stadium_id_fk` (`stadium_id`)," +
                    " KEY `match_team_id_fk` (`team_home_id`)," +
                    " KEY `match_team_id_fk_2` (`team_away_id`)," +
                    " CONSTRAINT `match_stadium_id_fk` FOREIGN KEY (`stadium_id`) REFERENCES `stadium` (`id`) ON DELETE CASCADE ON UPDATE CASCADE," +
                    " CONSTRAINT `match_team_id_fk` FOREIGN KEY (`team_home_id`) REFERENCES `team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE," +
                    " CONSTRAINT `match_team_id_fk_2` FOREIGN KEY (`team_away_id`) REFERENCES `team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE)" +
                    " ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

            private String INSERT_MATCH = "INSERT INTO premire_league.`match`" +
                    "(id, team_home_id, team_away_id, `date`, stadium_id)" +
                    "VALUES(?, ?, ?, ?,?);";
        }


        private static class Stadium {
            private static String CREATE_TABLE = " create table if not exists premire_league.stadium" +
                    " (id int primary key, name mediumtext not null, capacity bigint not null);";

            private static String INSERT_STADIUM = "INSERT INTO premire_league.stadium (id, name, capacity) VALUES(?, ?, ?);";
        }


        private static class Team {
            private static String CREATE_TABLE = "create table if not exists premire_league.team(" +
                    " id smallint  primary key,name mediumtext not null,  city_id smallint not null," +
                    "coach_id int not null, captain mediumtext not null, stadium_id smallint not null, point int default 0 not null," +
                    " constraint team_coach_id_fk foreign key (coach_id) references premire_league.coach (id)" +
                    " on update cascade on delete cascade);";

            private static String INSERT_TEAM = "INSERT INTO team" +
                    "(id,name, captain, city_id, coach_id, stadium_id)" +
                    "VALUES(?,?,?,?,?,?);";
        }

        private static class player {
            private static String CREATE_TABLE =
                    "  CREATE TABLE if not exist `player` (" +
                            " `id` smallint(6) NOT NULL," +
                            "`team_id` smallint(6) NOT NULL," +
                            "`first_name` varchar(10) NOT NULL," +
                            "`last_name` varchar(100) NOT NULL," +
                            "`age` int(11) NOT NULL," +
                            " `position` varchar(10) NOT NULL," +
                            " `contract` bigint(20) NOT NULL," +
                            "`salary` bigint(20) NOT NULL," +
                            "  PRIMARY KEY (`id`,`team_id`)," +
                            "KEY `player_FK` (`team_id`)," +
                            " CONSTRAINT `player_FK` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`))" +
                            "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

            private static String INSERT_PLAYER = "INSERT INTO player" +
                    "(id, team_id, first_name, last_name, age, `position`, contract, salary)" +
                    "VALUES(?,?,?,?,?,?,?,?);";
        }

        private static class playerTransfer {
            private static String CREATE_TABLE = "CREATE TABLE `player_transfer` (" +
                    " `id` int(11) NOT NULL AUTO_INCREMENT," +
                    " `player_id` smallint(6) NOT NULL," +
                    "`first_team_id` smallint(6) NOT NULL," +
                    "`second_team_id` smallint(6) NOT NULL," +
                    "`contract` bigint(20) NOT NULL," +
                    " `salary` bigint(20) NOT NULL," +
                    " PRIMARY KEY (`id`)," +
                    " KEY `player_transfer_team_id_fk` (`first_team_id`)," +
                    " KEY `player_transfer_team_id_fk_2` (`second_team_id`)," +
                    "CONSTRAINT `player_transfer_team_id_fk` FOREIGN KEY (`first_team_id`) REFERENCES `team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE," +
                    " CONSTRAINT `player_transfer_team_id_fk_2` FOREIGN KEY (`second_team_id`) REFERENCES `team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE)" +
                    "ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;";


            private static String INSERT_PLAYER_TRANSFER = "INSERT INTO player_transfer" +
                    "(player_id, first_team_id, second_team_id, contract, salary)" +
                    " VALUES(?,?,?,?,?);";
        }


        private static class TeamMatchPerformance {
            private static String CREATE_TABLE = "CREATE TABLE `team_match_performance` (" +
                    "`team_id` smallint(6) NOT NULL," +
                    "`match_id` smallint(6) NOT NULL," +
                    " `scored_golas` smallint(6) NOT NULL," +
                    "`recieved_goals` smallint(6) NOT NULL," +
                    " `point` smallint(6) NOT NULL," +
                    "  PRIMARY KEY (`team_id`,`match_id`)," +
                    "KEY `team_match_performance_FK_1` (`match_id`)," +
                    " CONSTRAINT `team_match_performance_FK` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "CONSTRAINT `team_match_performance_FK_1` FOREIGN KEY (`match_id`) REFERENCES `match` (`id`) ON DELETE CASCADE ON UPDATE CASCADE)" +
                    "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

            private static String INSERT_TEAM_MATCH_PERFORMANCE = "INSERT INTO team_match_performance" +
                    " (team_id, match_id, scored_golas, recieved_goals, `point`)" +
                    "VALUES(0, 0, 0, 0, 0);";
        }
    }

    private Connection getConnection() throws SQLException {
        return DbConnection.getConnection();
    }

    public void initializeDatabase() throws SQLException, DbException {
        try (Connection connection = getConnection();
             java.sql.Statement statement = connection.createStatement();) {

            try {
                statement.execute(Statement.Schema.CREATE);
            } catch (SQLException e) {
                throw new DbException("Error while creating Scheme", e);

            }

            try {
                statement.execute(Statement.City.CREATE_TABLE);
            } catch (SQLException e) {
                throw new DbException("Error while creating city table", e);

            }

            try {
                statement.execute(Statement.Coach.CREATE_TABLE);
            } catch (SQLException e) {
                throw new DbException("Error while creating Coach table", e);

            }

            try {
                statement.execute(Statement.CoachTransfer.CREATE_TABLE);
            } catch (SQLException e) {
                throw new DbException("Error while creating Coach table", e);

            }

            try {
                statement.execute(Statement.Match.CREATE_TABLE);
            } catch (SQLException e) {
                throw new DbException("Error while creating match table", e);

            }

            try {
                statement.execute(Statement.player.CREATE_TABLE);
            } catch (SQLException e) {
                throw new DbException("Error while creating player table", e);
            }

            try {
                statement.execute(Statement.playerTransfer.CREATE_TABLE);
            } catch (SQLException e) {
                throw new DbException("Error while creating player_transfer table", e);
            }

            try {
                statement.execute(Statement.Stadium.CREATE_TABLE);
            } catch (SQLException e) {
                throw new DbException("Error while creating stadium table", e);
            }

            try {
                statement.execute(Statement.Team.CREATE_TABLE);
            } catch (SQLException e) {
                throw new DbException("Error while creating team table", e);
            }

            try {
                statement.execute(Statement.TeamMatchPerformance.CREATE_TABLE);
            } catch (SQLException e) {
                throw new DbException("Error while creating team_match_performance table", e);
            }

        } catch (SQLException e) {
            throw new DbException("Error while connecting to the database", e);
        } catch (DbException e) {
            throw e;
        }

    }


    public void InsertingData() throws SQLException {
        Connection connection = DbConnection.getConnection();

        PreparedStatement cityPs = connection.prepareStatement(
                "INSERT INTO premire_league.city (id,name) VALUES" +
                        " (1,'Tehran')," +
                        " (2,'Esfahan')," +
                        "(4,'Tabriz'),");

        PreparedStatement coachPs = connection.prepareStatement(
                "  INSERT INTO premire_league.coach (id,first_name,last_name,team_id,contract) VALUES" +
                        " (17,'Yahya','Golmohammadi','1',9000000000)," +
                        " (18,'Moharram','Navidkia','2',8000000000)," +
                        " (20,'Farhad','Majidi','4',10000000000)," +
                        " (22,'Firooz','Karimi','6',6000000000);");


        PreparedStatement matchPs = connection.prepareStatement(
                " INSERT INTO premire_league.`match` (id,team_home_id,team_away_id,`date`,stadium_id) VALUES" +
                        "(1,1,2,'2020-07-10 20:30:00',1)," +
                        "(2,2,6,'2020-07-17 20:30:00',2)," +
                        "(3,4,6,'2020-07-24 20:30:00',1)," +
                        "(4,4,2,'2020-08-01 17:30:00',1)," +
                        "(5,1,4,'2020-08-09 18:15:00',1)," +
                        "(6,6,1,'2020-08-14 20:15:00',5)," +
                        "(7,2,1,'2020-09-01 20:15:00',1)," +
                        "(8,6,2,'2020-09-06 20:15:00',5)," +
                        "(9,6,4,'2020-09-12 18:20:00',5)," +
                        "(10,2,4,'2020-09-19 20:15:00',2)," +
                        "(11,4,1,'2020-09-24 16:20:00',1)," +
                        " (12,1,6,'2020-09-29 19:20:00',1);");


        PreparedStatement playerPs = connection.prepareStatement(

                "(1,4,'Hosein','Hoseni',30,'GK',3350000596,2500000)" +
                        "(1,6,'Mohammad','Akhbari',30,'Gk',2900000000,2800000)," +
                        "(4,1,'Jalal','Hoseini',36,'CB',4600000000,3600000)," +
                        " (10,4,'Mehdi','Ghaiedi',22,'RWF',3980000000,2600000)," +
                        "(13,6,'Arash','Ghaderi',25,'CB',2800000000,3600000)," +
                        "(18,4,'Alireza','Sadeghi',30,'CF',3000000000,3000000)," +
                        " (21,4,'Voria','Ghafouri',32,'RB',4800000000,4000000)," +
                        "(70,6,'Amin','Asadi',28,'CF',1500000000,3500000)," +
                        " (72,1,'Eisa','Alkasir',31,'CF',4500000000,3600000)," +
                        "(84,1,'Hamed','Lak',32,'GK',4000000000,4000000)," +
                        "(88,4,'Arash','Rezavand',25,'CMF',3000000000,3690000)," +
                        " (97,6,'Behzad','Salami',26,'CMF',1569000000,3400000);");


        PreparedStatement stadiumPs = connection.prepareStatement(
                "INSERT INTO premire_league.stadium(id, name, capacity)VALUES" +
                        "(1, 'Azadi', 78116)," +
                        " (2, 'Naghshe_jahan', 75000)," +
                        " (5, 'Yadehare_emam', 66833);");


        PreparedStatement teamPs = connection.prepareStatement(
                " (1,'Perspolis','Hoseini',1,17,1)," +
                        " (2,'Sepahan','Hajsafi',2,18,2)," +
                        " (4,'Esteghlal','Ghafouri',1,20,1)," +
                        " (6,'Terakhtor','Akhbari',4,22,5);");


        PreparedStatement tmp = connection.prepareStatement(
                "     (1,1,2,0,3)," +
                        " (1,5,1,2,0)," +
                        " (1,6,3,2,3)," +
                        " (1,7,3,3,1)," +
                        " (1,11,0,1,0)," +
                        " (1,12,2,1,3)," +
                        " (2,1,0,2,0)," +
                        " (2,2,2,0,3)," +
                        " (2,4,1,1,1)," +
                        "(2,7,3,3,1)," +
                        "(2,8,2,0,3)," +
                        "(2,10,0,1,0)," +
                        "(4,2,0,2,0)," +
                        "(4,3,1,0,3)," +
                        "(4,5,2,1,3)," +
                        "(4,8,0,2,0)," +
                        "(4,9,0,1,0)," +
                        "(4,11,1,0,3)," +
                        "(6,3,0,1,0)," +
                        "(6,4,0,1,0)," +
                        "(6,6,2,3,0)," +
                        "(6,9,1,0,0)," +
                        "(6,10,1,0,3)," +
                        "(6,12,1,2,0);");
    }


}
