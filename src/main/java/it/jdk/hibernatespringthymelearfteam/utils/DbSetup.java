package it.jdk.hibernatespringthymelearfteam.utils;

import it.jdk.hibernatespringthymelearfteam.domain.Player;
import it.jdk.hibernatespringthymelearfteam.domain.Team;
import it.jdk.hibernatespringthymelearfteam.domain._Role;
import it.jdk.hibernatespringthymelearfteam.domain._User;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alex
 */
@Component
public class DbSetup {

    private final static Logger logger = Logger.getLogger(DbSetup.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    public void initialize() {

        System.out.println("DbUtil initialize()");

        Team lazio = new Team("Lazio", "Lotito", 10000, 100);

        Player alex = new Player("Alex Mariani", 'D', "13");
        
        Player test =new Player("Test Prova",'A',"10");
        alex.setTeam(lazio);

        String password="password";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String cryptedPassword = passwordEncoder.encode(password);
        _Role roleAdmin = new _Role("ROLE_ADMIN");
        _User adminUser=new _User("alex", "mariani", "alex", cryptedPassword, Arrays.asList(roleAdmin));
        Session session = null;
        Transaction transaction = null;

        try {

            //init session and transaction
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            //save with session
            session.save(lazio);
            session.save(alex);
            session.save(test);
            session.save(adminUser);
            //commit transaction element saved
            transaction.commit();
        
        } catch (HibernateException hx) {
            //log exception
            logger.log(Level.SEVERE, hx.toString());
            
            //rollback if there is an error
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            
            //session closed
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
