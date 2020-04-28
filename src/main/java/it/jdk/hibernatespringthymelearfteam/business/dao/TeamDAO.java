/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.jdk.hibernatespringthymelearfteam.business.dao;


import it.jdk.hibernatespringthymelearfteam.domain.Team;
import java.util.Set;

/**
 *
 * @author Alex
 */
public interface TeamDAO {
     public void delete(long uid);

    public Set<Team> findAllTeams();

    public Team findByUid(Long uid);

    public Team findByName(String name);

    public void save(Team team);

    public void update(Team team);
}
