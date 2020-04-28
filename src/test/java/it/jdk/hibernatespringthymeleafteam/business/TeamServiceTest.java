/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.jdk.hibernatespringthymeleafteam.business;

import it.jdk.hibernatespringthymeleafteam.commons.TestConfiguration;
import it.jdk.hibernatespringthymelearfteam.business.TeamBO;
import it.jdk.hibernatespringthymelearfteam.domain.Team;
import javax.transaction.Transactional;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Alex
 */
@RunWith(SpringRunner.class)
@TestConfiguration
@Transactional
public class TeamServiceTest {

    @Autowired
    private TeamBO teambo;

    @Test
    public void CreateTeamTest() {

        //Create Team and Save team on MySQL
        Team team = new Team("Lazio", "Lotito", 1000, 10);
        teambo.save(team);

        //Recover team and verify team information
        Team teamP = teambo.findByUid(team.getId());
        Assert.assertNotNull(teamP);
        Assert.assertEquals(teamP, team);

    }
    
    /*@Test
    public void UpdateTeamTest(){
        //Create Team and Save team on MySQL
        Team team = new Team("Lazio", "Lotito", 1000, 10);
        teambo.save(team);

        //Update team on db
        Team teamU = new Team("Lazio1900", "Lotito", 1000, 10);
        teamU.setId(team.getId());
        teambo.update(teamU);
        
        //Recover team and verify team information
        Team teamT = teambo.findByUid(teamU.getId());
        
        Assert.assertNotNull(teamT);
        Assert.assertEquals(teamT, teamU);
    }*/

}
