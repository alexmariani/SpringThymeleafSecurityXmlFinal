package it.jdk.hibernatespringthymelearfteam.business;

import it.jdk.hibernatespringthymelearfteam.domain._User;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Alex
 */
public interface UserBO {

    public _User findByUsername(String username);

    public void save(_User user);

    public Collection<_User> findAll();
}
