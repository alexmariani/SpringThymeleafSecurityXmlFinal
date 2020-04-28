package it.jdk.hibernatespringthymelearfteam.business.dao;

import it.jdk.hibernatespringthymelearfteam.domain.Fan;
import it.jdk.hibernatespringthymelearfteam.domain.Player;
import java.util.Set;

/**
 *
 * @author Alex
 */
public interface FanDAO {

    public void delete(long uid);

    public Set<Fan> findAllFans();

    public Fan findByUid(Long uid);

    public Fan findByName(String name);

    public void save(Fan fan);

    public void update(Fan fan);
    
    public Set<Player> FindPlayerById(Long id);
}
