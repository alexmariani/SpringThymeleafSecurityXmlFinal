
package it.jdk.hibernatespringthymelearfteam.business.impl;

import it.jdk.hibernatespringthymelearfteam.business.FanBO;
import it.jdk.hibernatespringthymelearfteam.business.dao.FanDAO;
import it.jdk.hibernatespringthymelearfteam.domain.Fan;
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
public class FanBOImpl implements FanBO {

    @Autowired
    private FanDAO fandao;

    @Override
    public void delete(long uid) {
        fandao.delete(uid);
    }

    @Override
    public Set<Fan> findAllFans() {
        return fandao.findAllFans();
    }

    @Override
    public Fan findByUid(Long uid) {
        return fandao.findByUid(uid);
    }

    @Override
    public Fan findByName(String name) {
        return fandao.findByName(name);
    }

    @Override
    public void save(Fan fan) {
        fandao.save(fan);
    }

    @Override
    public void update(Fan fan) {
        fandao.update(fan);
    }
    
}
