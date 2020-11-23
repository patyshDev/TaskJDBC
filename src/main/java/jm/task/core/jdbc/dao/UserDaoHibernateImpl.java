package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Session session = null;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery("create table if not exists users(" +
                    "id bigint auto_increment primary key," +
                    "name varchar(45) not null," +
                    "lastname varchar(45) not null," +
                    "age tinyint not null);");
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery("drop table if exists users");
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = Util.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery(String.format("insert into users (name, lastname, age)" +
                    " values ('%s', '%s', %d);", name, lastName, age));
            query.executeUpdate();
            tx.commit();
            session.close();
            System.out.println("User с именем – " +  name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = Util.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery("delete from users where id = " + id);
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            session = Util.getSessionFactory().openSession();
            users = session.createSQLQuery("select * from users").addEntity(User.class).list();
            for (User user: users)
                System.out.println(user);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery("delete from users");
            query.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
