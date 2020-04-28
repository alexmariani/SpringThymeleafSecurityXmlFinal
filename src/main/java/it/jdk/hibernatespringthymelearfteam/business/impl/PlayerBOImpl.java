package it.jdk.hibernatespringthymelearfteam.business.impl;

import it.jdk.hibernatespringthymelearfteam.business.PlayerBO;
import it.jdk.hibernatespringthymelearfteam.business.dao.PlayerDAO;
import it.jdk.hibernatespringthymelearfteam.domain.Player;
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
public class PlayerBOImpl implements PlayerBO{

    @Autowired
    private PlayerDAO playerdao;
    
    
    @Override
    public void delete(long uid) {
        playerdao.delete(uid);
    }

    @Override
    public Set<Player> findAllPlayers() {
        return playerdao.findAllPlayers();
    }

    @Override
    public Player findByUid(Long uid) {
        return playerdao.findByUid(uid);
    }

    @Override
    public Player findByName(String name) {
        return playerdao.findByName(name);
    }

    @Override
    public void save(Player player) {
        playerdao.save(player);
    }

    @Override
    public void update(Player player) {
        playerdao.update(player);
    }
    
}
