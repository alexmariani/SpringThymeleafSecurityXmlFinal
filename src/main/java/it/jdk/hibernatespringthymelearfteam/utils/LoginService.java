
package it.jdk.hibernatespringthymelearfteam.utils;

import it.jdk.hibernatespringthymelearfteam.business.UserBO;
import it.jdk.hibernatespringthymelearfteam.domain._Role;
import it.jdk.hibernatespringthymelearfteam.domain._User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alex
 */
@Service("userDetailsService")
@Transactional(noRollbackFor = Exception.class)
public class LoginService implements UserDetailsService {
    @Autowired
    private UserBO userbo;

    @Override
    
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        _User userDb=userbo.findByUsername(username);
        User user = new User(userDb.getUsername(), userDb.getPassword(), 
                userDb.isEnabled(), true, true, true, getAuthorities(userDb.getRoles()));
        return user;
    }

    public final Collection<? extends GrantedAuthority> getAuthorities(final Collection<_Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (_Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
