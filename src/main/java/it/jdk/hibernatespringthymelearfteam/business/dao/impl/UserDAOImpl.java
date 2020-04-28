package it.jdk.hibernatespringthymelearfteam.business.dao.impl;

import it.jdk.hibernatespringthymelearfteam.business.dao.UserDAO;
import it.jdk.hibernatespringthymelearfteam.domain._User;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alex
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());

    @Override
    public _User findByUsername(String username) {
        _User user = null;
        try {
            Query q = sessionFactory
                    .getCurrentSession()
                    .createQuery("FROM _User WHERE username=:username")
                    .setParameter("username", username);
            user = (_User) q.uniqueResult();
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex.getMessage());
        }

        return user;
    }

    @Override
    public Collection<_User> findAll() {
        String hql = "From _User";
        List<_User> users = this.sessionFactory.getCurrentSession()
                .createQuery(hql)
                .list();
        return users;
    }

    @Override
    public void save(_User user) {
        sessionFactory.getCurrentSession().save(user);
    }
}
