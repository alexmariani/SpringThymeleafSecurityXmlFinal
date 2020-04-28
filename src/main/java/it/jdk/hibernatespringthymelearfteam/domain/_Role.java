package it.jdk.hibernatespringthymelearfteam.domain;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "roles")
public class _Role extends AbstractEntity{
    @ManyToMany(mappedBy = "roles")
    private Collection<_User> users;

    private String name;

    public _Role(final String name) {
        this.name = name;
    }
    public _Role(){}

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Collection<_User> getUsers() {
        return users;
    }

    public void setUsers(final Collection<_User> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.name);
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
        final _Role other = (_Role) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Role [name=").append(name).append("]")
                .append("[id=").append(getId()).append("]");
        return builder.toString();
    }
}
