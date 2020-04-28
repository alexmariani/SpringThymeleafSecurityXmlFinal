package it.jdk.hibernatespringthymelearfteam.business.impl;

import it.jdk.hibernatespringthymelearfteam.business.RoleBO;
import it.jdk.hibernatespringthymelearfteam.business.dao.RoleDAO;
import it.jdk.hibernatespringthymelearfteam.domain._Role;
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
public class RoleBOImpl implements RoleBO {

    @Autowired
    private RoleDAO roledao;

    @Override
    public void save(_Role role) {
        roledao.save(role);
    }

    @Override
    public Set<_Role> findAll() {
        return roledao.findAll();
    }

    @Override
    public void delete(_Role role) {
        roledao.delete(role);
    }

    @Override
    public _Role findById(Long id) {
        return roledao.findById(id);
    }

    @Override
    public _Role findByRoleName(String roleName) {
        return roledao.findByRoleName(roleName);
    }
    @Override
    public void update(_Role role){
        roledao.update(role);
    }

}
