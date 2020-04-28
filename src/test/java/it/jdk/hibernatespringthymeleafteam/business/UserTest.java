/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.jdk.hibernatespringthymeleafteam.business;

import it.jdk.hibernatespringthymeleafteam.commons.TestConfiguration;
import it.jdk.hibernatespringthymelearfteam.business.UserBO;
import it.jdk.hibernatespringthymelearfteam.domain._Role;
import it.jdk.hibernatespringthymelearfteam.domain._User;
import java.util.Arrays;
import java.util.List;
import javax.transaction.Transactional;
import junit.framework.Assert;
import org.h2.engine.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Alex
 *//*
@RunWith(SpringRunner.class)
@TestConfiguration
@Transactional
public class UserTest {

    @Autowired
    private UserBO userbo;

    @Test
    public void CreateUser() {
        _Role roleAdmin = new _Role("ROLE_ADMIN");
        String password = "password";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String cryptedPassword = passwordEncoder.encode(password);
        _User user = new _User("alex", "mariani", "alex",cryptedPassword,Arrays.asList(roleAdmin));
        userbo.save(user);
        
        _User test=userbo.findByUsername(user.getUsername());
        Assert.assertNotNull(test);
        Assert.assertEquals(user,test);
    }
    
}*/
