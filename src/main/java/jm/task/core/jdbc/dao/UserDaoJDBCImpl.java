package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Util util;

    private String sqlCommandCreate = "CREATE TABLE Users (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), LastName VARCHAR(20), Age SMALLINT(3) )";
    private String sqlCommandDeleteUser = "DELETE FROM Users WHERE Id = ";
    private String sqlCommandGetAllUser = "SELECT * FROM Users";
    private String sqlCommandDeleteAllUser = "TRUNCATE TABLE Users";
    private String sqlCommandDropTable = "DROP TABLE Users";

    public UserDaoJDBCImpl() {
        util = new Util();
    }

    public void createUsersTable() {
        try(Connection conn = util.getConnection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlCommandCreate);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            // throwables.printStackTrace();
            System.out.println("Table already exist");
        }
    }

    public void dropUsersTable() {
        try(Connection conn = util.getConnection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlCommandDropTable);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            // throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection conn = util.getConnection()) {
            Statement statement = conn.createStatement();
            System.out.println(createSQLCommandSetUser(name, lastName, age));
            statement.executeUpdate(createSQLCommandSetUser(name, lastName, age));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            // throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection conn = util.getConnection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlCommandDeleteUser + id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            // throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = null;
        try(Connection conn = util.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommandGetAllUser);
            User user;
            users = new ArrayList<>();
            while(resultSet.next()) { // read all data
                user = new User();
                user.setId((long)resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                users.add(user);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            // throwables.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Connection conn = util.getConnection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlCommandDeleteAllUser);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            // throwables.printStackTrace();
        }
    }

    private String createSQLCommandSetUser(String name, String lastName, byte age) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT Users(Name, LastName, Age) VALUES ('")
                .append(name)
                .append("','")
                .append(lastName)
                .append("',")
                .append(age)
                .append(')');
        return sb.toString();
    }
}
