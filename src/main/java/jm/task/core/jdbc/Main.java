package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Denis", "Rodman", (byte) 59);
        userDaoJDBC.saveUser("Michael", "Jordan", (byte) 57);
        userDaoJDBC.saveUser("Scottie", "Pippen", (byte) 55);
        userDaoJDBC.saveUser("Derrick", "Rose", (byte) 32);
        userDaoJDBC.getAllUsers();
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Denis", "Rodman", (byte) 59);
        userDaoHibernate.saveUser("Michael", "Jordan", (byte) 57);
        userDaoHibernate.saveUser("Scottie", "Pippen", (byte) 55);
        userDaoHibernate.saveUser("Derrick", "Rose", (byte) 32);
        userDaoHibernate.getAllUsers();
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}
