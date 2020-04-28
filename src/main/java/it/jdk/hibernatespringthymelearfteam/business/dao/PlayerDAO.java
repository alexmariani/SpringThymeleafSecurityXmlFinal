/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.jdk.hibernatespringthymelearfteam.business.dao;

import it.jdk.hibernatespringthymelearfteam.domain.Player;
import java.util.Set;

/**
 *
 * @author Alex
 */
public interface PlayerDAO {
    public void delete(long uid);

    public Set<Player> findAllPlayers();

    public Player findByUid(Long uid);

    public Player findByName(String name);

    public void save(Player player);

    public void update(Player player);
}
