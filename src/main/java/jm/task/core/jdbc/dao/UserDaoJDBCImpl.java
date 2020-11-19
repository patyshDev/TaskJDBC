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

    private static final String TABLE_CREATE = "create table if not exists users(" +
            "id bigint auto_increment primary key," +
            "name varchar(45) not null," +
            "lastname varchar(45) not null," +
            "age tinyint not null);";
    private static final String TABLE_NAME = "users";
    private static final String TABLE_DELETE = "drop table if exists users";
    private static final String TABLE_CLEAN = "delete from users";
    private static final String DELETE_LINE = "delete from users where id = ";
    private static final String SAVE_USERS = "insert into users (name, lastname, age) values ";
    private static final String ALL_USERS = "select * from users";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Statement statement = Util.getConnection().createStatement()){
            statement.execute(TABLE_CREATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Statement statement = Util.getConnection().createStatement()){
            statement.execute(TABLE_DELETE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Statement statement = Util.getConnection().createStatement()){
            statement.execute(SAVE_USERS + "('" + name + "', '" + lastName + "', " + age + ")");
            System.out.println("User с именем – " +  name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Statement statement = Util.getConnection().createStatement()){
            statement.execute(DELETE_LINE + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try(Statement statement = Util.getConnection().createStatement()){
            ResultSet result = statement.executeQuery(ALL_USERS);
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong(1));
                user.setName(result.getString(2));
                user.setLastName(result.getString(3));
                user.setAge(result.getByte(4));
                usersList.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;
    }

    public void cleanUsersTable() {
        try(Statement statement = Util.getConnection().createStatement()){
            statement.execute(TABLE_CLEAN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
