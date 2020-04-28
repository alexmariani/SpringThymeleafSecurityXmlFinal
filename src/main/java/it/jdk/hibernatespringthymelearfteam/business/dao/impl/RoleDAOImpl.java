package it.jdk.hibernatespringthymelearfteam.business.dao.impl;

import it.jdk.hibernatespringthymelearfteam.business.dao.RoleDAO;
import it.jdk.hibernatespringthymelearfteam.domain._Role;
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
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(_Role role) {
        this.sessionFactory.getCurrentSession().save(role);
    }

    @Override
    public Set<_Role> findAll() {
        List<_Role> rolesList = (List<_Role>) this.sessionFactory
                .getCurrentSession().createQuery("From _Role").list();
        Set<_Role> roles = new HashSet(rolesList);
        return roles;
    }

    @Override
    public void delete(_Role role) {
        this.sessionFactory.getCurrentSession().remove(role);
    }

    @Override
    public _Role findById(Long id) {
        String hql = "FROM _Role WHERE id= :id";
        _Role role = (_Role) this.sessionFactory
                .getCurrentSession()
                .createQuery(hql)
                .setParameter("id", id)
                .uniqueResult();
        return role;
    }

    @Override
    public _Role findByRoleName(String roleName) {
        String hql = "FROM _Role WHERE name= :name";
        _Role role = (_Role) this.sessionFactory
                .getCurrentSession()
                .createQuery(hql)
                .setParameter("name", roleName)
                .uniqueResult();
        return role;
    }

    @Override
    public void update(_Role role){
        this.sessionFactory.getCurrentSession().update(role);
    }

}
