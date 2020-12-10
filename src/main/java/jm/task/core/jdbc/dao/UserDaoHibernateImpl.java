package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS Users (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), LastName VARCHAR(20), Age SMALLINT(3) )";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.persist(user);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Query deleteQuery = session.createQuery("delete from User e where e.id=?" + id);
            deleteQuery.setParameter((int)id, id);
            deleteQuery.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try(Session session = Util.getSessionFactory().openSession()) {
            Query<User> query  = session.createQuery("select e from User e");
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            String hql = String.format("delete from User");
            Query query = session.createQuery(hql);
            query.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
