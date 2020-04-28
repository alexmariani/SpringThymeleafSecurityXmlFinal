package it.jdk.hibernatespringthymelearfteam.domain;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "teams")
public class Team extends AbstractEntity {

    @NotEmpty
    @NotNull
    @Size(min = 4, max = 15)
    private String name;

    @NotEmpty
    @NotNull
    private String owner;

    @NotNull
    private long funds;

    @NotNull
    private long tot_employeer;

    @OneToMany(mappedBy = "team")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Player> players;

    @ManyToMany(mappedBy="teams")
    private Set<Fan> fans;

    public Team(String name, String owner, long funds, long tot_employeer) {
        this.name = name;
        this.owner = owner;
        this.funds = funds;
        this.tot_employeer = tot_employeer;
    }

    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public long getFunds() {
        return funds;
    }

    public void setFunds(long funds) {
        this.funds = funds;
    }

    public long getTot_employeer() {
        return tot_employeer;
    }

    public void setTot_employeer(long tot_employeer) {
        this.tot_employeer = tot_employeer;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Set<Fan> getFans() {
        return fans;
    }

    public void setFans(Set<Fan> fans) {
        this.fans = fans;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.owner);
        hash = 59 * hash + (int) (this.funds ^ (this.funds >>> 32));
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
        final Team other = (Team) obj;
        return true;
    }

}
