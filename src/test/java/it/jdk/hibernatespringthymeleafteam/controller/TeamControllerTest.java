
package it.jdk.hibernatespringthymeleafteam.controller;

import it.jdk.hibernatespringthymeleafteam.commons.TestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Alex
 */
@RunWith(SpringRunner.class)
@TestConfiguration
public class TeamControllerTest {
   
    
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private final String controllerContext = "/team";
    
    
    @Before
    public void prepare() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void teamHome() throws Exception {
        this.mockMvc.perform(get(controllerContext + "/home"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("teams"))
                .andExpect(model().attributeExists("players"))
                .andExpect(model().attributeExists("fans"))
                .andExpect(view().name("views/team"));
    }
    
   
}
