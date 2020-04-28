
package it.jdk.hibernatespringthymelearfteam.business.impl;

import it.jdk.hibernatespringthymelearfteam.business.TeamBO;
import it.jdk.hibernatespringthymelearfteam.business.dao.TeamDAO;
import it.jdk.hibernatespringthymelearfteam.domain.Team;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alex
 */
@Service
@Transactional
public class TeamBOImpl implements TeamBO {
    
    
    @Autowired
    private TeamDAO teamdao;

    @Override
    public void delete(long uid) {
        teamdao.delete(uid);
    }

    @Override
    public Set<Team> findAllTeams() {
      return  teamdao.findAllTeams();
    }

    @Override
    public Team findByUid(Long uid) {
        return teamdao.findByUid(uid);
    }

    @Override
    public Team findByName(String name) {
        return teamdao.findByName(name);
    }

    @Override
    public void save(Team team) {
        teamdao.save(team);
    }

    @Override
    public void update(Team team) {
        teamdao.update(team);
    }
    
    
}
