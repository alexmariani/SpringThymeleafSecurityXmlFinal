package it.jdk.hibernatespringthymelearfteam.business.dao.impl;

import it.jdk.hibernatespringthymelearfteam.business.dao.PlayerDAO;
import it.jdk.hibernatespringthymelearfteam.domain.Fan;
import it.jdk.hibernatespringthymelearfteam.domain.Player;
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
public class PlayerDAOImpl implements PlayerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void delete(long uid) {
        Player player = (Player) sessionFactory.getCurrentSession().get(Player.class, uid);

        if (player.getFans() != null) {
            for (Fan fan : player.getFans()) {
                for (Player p : fan.getPlayers()) {
                    if (p.getFullname() == player.getFullname()) {
                        fan.getTeams().remove(p);
                    }
                }
            }
        }
        
        player.setFans(null);
        player.setTeam(null);

        sessionFactory.getCurrentSession().delete(player);
    }

    @Override
    public Set<Player> findAllPlayers() {
        List<Player> playerlist = (List<Player>) sessionFactory.getCurrentSession().
                createQuery("FROM Player").list();
        Set<Player> player = new HashSet(playerlist);
        return player;
    }

    @Override
    public Player findByUid(Long uid) {
        Player player = (Player) sessionFactory.getCurrentSession().get(Player.class, uid);
        return player;
    }

    @Override
    public Player findByName(String name) {
        Player player;
        Query q = sessionFactory
                .getCurrentSession()
                .createQuery("FROM Player where fullname = :name")
                .setParameter("name", name);
        player = (Player) q.uniqueResult();
        return player;
    }

    @Override
    public void save(Player player) {
        if (player.getId() == null) {
            sessionFactory.getCurrentSession().save(player);
        } else {
            sessionFactory.getCurrentSession().update(player);
        }
    }

    @Override
    public void update(Player player) {
        sessionFactory.getCurrentSession().update(player);
    }

}
