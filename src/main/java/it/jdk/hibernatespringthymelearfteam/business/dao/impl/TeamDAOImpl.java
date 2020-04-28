/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.jdk.hibernatespringthymelearfteam.business.dao.impl;

import org.hibernate.SessionFactory;
import it.jdk.hibernatespringthymelearfteam.business.dao.TeamDAO;
import it.jdk.hibernatespringthymelearfteam.domain.Fan;
import it.jdk.hibernatespringthymelearfteam.domain.Player;
import it.jdk.hibernatespringthymelearfteam.domain.Team;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alex
 */
@Repository
public class TeamDAOImpl implements TeamDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void delete(long uid) {
        Team team = (Team) sessionFactory.getCurrentSession().get(Team.class, uid);

        for (Fan fan : team.getFans()) {
            for (Team t : fan.getTeams()) {
                if (t.getName() == team.getName()) {
                    fan.getTeams().remove(t);
                }
            }
        }

        for (Player player : team.getPlayers()) {
            player.setTeam(null);
        }

        team.setFans(null);
        team.setPlayers(null);

        sessionFactory.getCurrentSession().delete(team);
    }

    @Override
    public Set<Team> findAllTeams() {
        String q="FROM Team";
        List<Team> listteam = (List<Team>) sessionFactory.getCurrentSession().
                createQuery(q).list();
        Set<Team> team = new HashSet(listteam);
        return team;
    }

    @Override
    public Team findByUid(Long uid) {
        Team team = (Team) sessionFactory.getCurrentSession().get(Team.class, uid);
        return team;
    }

    @Override
    public Team findByName(String name) {
        Team team;
        Query q = sessionFactory
                .getCurrentSession()
                .createQuery("FROM Team where name = :name")
                .setParameter("name", name);
        team = (Team) q.uniqueResult();
        return team;
    }

    @Override
    public void save(Team team) {
        if (team.getId() == null) {
            sessionFactory.getCurrentSession().save(team);
        } else {
            sessionFactory.getCurrentSession().update(team);
        }
    }

    @Override
    public void update(Team team) {
        sessionFactory.getCurrentSession().update(team);

    }

}
