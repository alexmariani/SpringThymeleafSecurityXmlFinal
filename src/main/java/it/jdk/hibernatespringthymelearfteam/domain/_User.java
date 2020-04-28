package it.jdk.hibernatespringthymelearfteam.domain;

import java.util.Collection;
import java.util.Objects;
import static java.util.stream.Collectors.toList;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/**
 *
 * @author Alex
 */

@Entity
@Table(name="users")
public class _User extends AbstractEntity implements UserDetails {
   
    private String firstName;

    private String lastName;

  
    private String username;

    
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private Collection<_Role> roles;

    public _User(String firstName, String lastName, String username, String password, Collection<_Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public _User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Collection<_Role> getRoles() {
        return roles;
    }

    public void setRoles(final Collection<_Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.firstName);
        hash = 61 * hash + Objects.hashCode(this.lastName);
        hash = 61 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final _User other = (_User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + getId() + ", firstName=" + firstName + ", lastName="
                + lastName + ", username=" + username + ", password=" + password
                + ", enabled=" + isEnabled() + '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map( 
                role -> new SimpleGrantedAuthority(role.getName()))
                .collect(toList());
    }
}
