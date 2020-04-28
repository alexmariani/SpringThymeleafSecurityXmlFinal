
package it.jdk.hibernatespringthymelearfteam.business.impl;

import it.jdk.hibernatespringthymelearfteam.business.dao.UserDAO;
import it.jdk.hibernatespringthymelearfteam.domain._User;
import it.jdk.hibernatespringthymelearfteam.business.UserBO;
import java.util.Collection;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alex
 */
@Service
@Transactional
public class UserBOImpl implements UserBO {

    @Autowired
    private UserDAO userdao;

    @Override
    public _User findByUsername(String username) {
        return userdao.findByUsername(username);
    }

    @Override
    public void save(_User user) {
        userdao.save(user);
    }

    @Override
    public Collection<_User> findAll() {
        return userdao.findAll();
    }
    
    
    
}
