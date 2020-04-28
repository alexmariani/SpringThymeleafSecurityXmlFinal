package it.jdk.hibernatespringthymelearfteam.business;

import it.jdk.hibernatespringthymelearfteam.domain.Team;
import java.util.Set;

/**
 *
 * @author Alex
 */
public interface TeamBO {

    public void delete(long uid);

    public Set<Team> findAllTeams();

    public Team findByUid(Long uid);

    public Team findByName(String name);

    public void save(Team team);

    public void update(Team team);
}
