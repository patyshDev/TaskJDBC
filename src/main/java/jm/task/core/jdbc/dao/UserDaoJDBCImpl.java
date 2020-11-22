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

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Statement statement = Util.getConnection().createStatement()){
            statement.execute("create table if not exists users(" +
                    "id bigint auto_increment primary key," +
                    "name varchar(45) not null," +
                    "lastname varchar(45) not null," +
                    "age tinyint not null);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Statement statement = Util.getConnection().createStatement()){
            statement.execute("drop table if exists users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Statement statement = Util.getConnection().createStatement()){
            statement.execute(String.format("insert into users (name, lastname, age)" +
                    " values ('%s', '%s', %d);", name, lastName, age));
            System.out.println("User с именем – " +  name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Statement statement = Util.getConnection().createStatement()){
            statement.execute("delete from users where id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try(Statement statement = Util.getConnection().createStatement()){
            ResultSet result = statement.executeQuery("select * from users");
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
            statement.execute("delete from users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
