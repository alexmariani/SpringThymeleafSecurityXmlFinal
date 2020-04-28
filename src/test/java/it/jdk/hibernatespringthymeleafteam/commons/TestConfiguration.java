package it.jdk.hibernatespringthymeleafteam.commons;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author Alex
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/application-context.xml", 
    "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml","file:src/main/webapp/WEB-INF/context-security.xml"})
@ActiveProfiles("test")
public @interface TestConfiguration {
    
}
