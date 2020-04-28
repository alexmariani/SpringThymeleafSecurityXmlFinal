
package it.jdk.hibernatespringthymelearfteam.business;

import it.jdk.hibernatespringthymelearfteam.domain.Player;
import java.util.Set;

/**
 *
 * @author Alex
 */
public interface PlayerBO {
    public void delete(long uid);

    public Set<Player> findAllPlayers();

    public Player findByUid(Long uid);

    public Player findByName(String name);

    public void save(Player player);

    public void update(Player player);
}
