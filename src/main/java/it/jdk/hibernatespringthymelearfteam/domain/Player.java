package it.jdk.hibernatespringthymelearfteam.domain;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alex
 */
@Entity
@Table(name="players")
public class Player extends AbstractEntity{
    
    @NotEmpty
    @NotNull
    private String fullname;
    
    @NotNull
    private char position;
    
    @NotNull
    @NotEmpty
    private String number;
    
    @ManyToOne
    private Team team;
    
    @ManyToMany(mappedBy="players")
    private Set<Fan> fans;

    public Player() {
    }

    public Player(String fullname, char position, String number) {
        this.fullname = fullname;
        this.position = position;
        this.number = number;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public char getPosition() {
        return position;
    }

  
    public void setPosition(char position) {
        this.position = position;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<Fan> getFans() {
        return fans;
    }

    public void setFans(Set<Fan> fans) {
        this.fans = fans;
    }

  @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.fullname);
        hash = 41 * hash + this.position;
        hash = 41 * hash + Objects.hashCode(this.number);
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
        final Player other = (Player) obj;
        if (this.position != other.position) {
            return false;
        }
        if (!Objects.equals(this.fullname, other.fullname)) {
            return false;
        }
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        return true;
    }


}
