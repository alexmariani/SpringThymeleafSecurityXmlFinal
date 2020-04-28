package it.jdk.hibernatespringthymelearfteam.business.dao.impl;

import it.jdk.hibernatespringthymelearfteam.business.dao.FanDAO;
import it.jdk.hibernatespringthymelearfteam.domain.Fan;
import it.jdk.hibernatespringthymelearfteam.domain.Player;
import it.jdk.hibernatespringthymelearfteam.domain.Team;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alex
 */
@Repository
public class FanDAOImpl implements FanDAO {

    @Autowired
    private SessionFactory sessionFactory;

    
    @Override
    public void delete(long uid) {
        Fan fan = (Fan) sessionFactory.getCurrentSession().get(Fan.class, uid);

        for (Player player : fan.getPlayers()) {
            for (Fan f : player.getFans()) {
                if (f.getFullname() == fan.getFullname()) {
                    player.getFans().remove(f);
                }
            }
        }

        for (Team team : fan.getTeams()) {
            for (Fan f : team.getFans()) {
                if (f.getFullname() == fan.getFullname()) {
                    team.getFans().remove(f);
                }
            }
        }
        fan.setTeams(null);
        fan.setPlayers(null);
        sessionFactory.getCurrentSession().delete(fan);
    }

    @Override
    public Set<Fan> findAllFans() {

        List<Fan> fanlist = (List<Fan>) sessionFactory.getCurrentSession()
                .createQuery("From Fan").list();

        Set<Fan> fan = new HashSet(fanlist);
        return fan;
    }

    @Override
    public Fan findByUid(Long uid) {
        Fan fan = (Fan) sessionFactory.getCurrentSession().get(Fan.class, uid);
        return fan;
    }

    @Override
    public Fan findByName(String name) {
        Fan fan;
        Query q = sessionFactory
                .getCurrentSession()
                .createQuery("FROM Fan where fullname=:name")
                .setParameter("fullname", name);
        fan = (Fan) q.uniqueResult();
        return fan;
    }

    @Override
    public void save(Fan fan) {
        if (fan.getId() == null) {
            sessionFactory.getCurrentSession().save(fan);
        } else {
            sessionFactory.getCurrentSession().update(fan);

        }
    }

    @Override
    public void update(Fan fan) {
        sessionFactory.getCurrentSession().update(fan);
    }

    @Override
    public Set<Player> FindPlayerById(Long id) {
        Set<Player> fanPlayer=null;
        return null;
        
    }

}
