package it.jdk.hibernatespringthymelearfteam.business.dao;

import it.jdk.hibernatespringthymelearfteam.domain._Role;
import java.util.Set;

/**
 *
 * @author Alex
 */
public interface RoleDAO {

    public void save(_Role role);

    public Set<_Role> findAll();

    public void delete(_Role role);

    public _Role findById(Long id);

    public _Role findByRoleName(String roleName);

    public void update(_Role role);
}
