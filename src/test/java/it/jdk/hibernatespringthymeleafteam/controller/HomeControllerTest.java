package it.jdk.hibernatespringthymeleafteam.controller;

import it.jdk.hibernatespringthymelearfteam.controller.HomeController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author Alex
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class HomeControllerTest {

    private final HomeController homeController = new HomeController();

    private MockMvc mockMvc;

    @Before
    public void prepare() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void home() throws Exception {
       this.mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("views/index"));
    }
}
