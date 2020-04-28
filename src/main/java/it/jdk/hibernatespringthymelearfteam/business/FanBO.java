
package it.jdk.hibernatespringthymelearfteam.business;

import it.jdk.hibernatespringthymelearfteam.domain.Fan;
import java.util.Set;

/**
 *
 * @author Alex
 */
public interface FanBO {

    public void delete(long uid);

    public Set<Fan> findAllFans();

    public Fan findByUid(Long uid);

    public Fan findByName(String name);

    public void save(Fan fan);

    public void update(Fan fan);
}
