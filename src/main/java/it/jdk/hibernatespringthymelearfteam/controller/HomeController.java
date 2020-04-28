package it.jdk.hibernatespringthymelearfteam.controller;

import it.jdk.hibernatespringthymelearfteam.business.RoleBO;
import it.jdk.hibernatespringthymelearfteam.business.UserBO;
import it.jdk.hibernatespringthymelearfteam.domain._Role;
import it.jdk.hibernatespringthymelearfteam.domain._User;
import it.jdk.hibernatespringthymelearfteam.utils.ClientResponse;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Alex
 */
@Controller
public class HomeController {

    private final static Logger log = Logger.getLogger(HomeController.class);

    @Autowired
    private RoleBO rolebo;

    @Autowired
    private UserBO userbo;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    @Qualifier("sessionRegistry")
    private SessionRegistry sessionRegistry;

    @RequestMapping({"", "/home"})
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("views/index");
        return new ModelAndView("views/index");
    }

    @RequestMapping(value = "/login.html")
    public String login() {
        return "views/login";
    }

    @RequestMapping(value = "/403")
    public String accessDenied() {
        return "views/denied";
    }

    /*@RequestMapping(value = "/welcome")
    public ModelAndView welcome(@RequestHeader("user-agent") String userAgent, @CookieValue("JSESSIONID") String cookie) {
        return new ModelAndView("views/welcome");
    }*/
    
    @RequestMapping(value = "/signup")
    public ModelAndView reg() {
        ModelAndView mav = new ModelAndView("views/signup");
        mav.addObject("roles", rolebo.findAll());
        return mav;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute("newUser") _User user,
            final RedirectAttributes redirectAttributes,
            HttpServletRequest hsr) {

        log.info("Registration save");

        try {
            String password = user.getPassword();
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String cryptedPassword = passwordEncoder.encode(password);
            user.setPassword(cryptedPassword);
            userbo.save(user);
            redirectAttributes.addFlashAttribute("response", new ClientResponse(true, messageSource
                    .getMessage("feedback.messages.success", null, localeResolver.resolveLocale(hsr))));
        } catch (DataAccessException dae) {
            log.debug(dae.getMessage());
            redirectAttributes.addFlashAttribute("response",
                    new ClientResponse(false, dae.getMessage()));
        }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/profile")
    public ModelAndView profile() {
        ModelAndView mav = new ModelAndView("views/profile");
        List<Object> activeUsers=sessionRegistry.getAllPrincipals();
        Object user=null;
 
        return mav;
    }

    @InitBinder("newUser")
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(Collection.class,
                "roles",
                new CustomCollectionEditor(Collection.class) {
            @Override
            protected Object convertElement(Object element) {
                if (element instanceof String) {
                    String idStr = (String) element;
                    _Role role = null;
                    try {
                        long id = Long.parseLong(idStr);
                        role = rolebo.findById(id);
                    } catch (NumberFormatException nfe) {
                        System.err.println(nfe.getMessage());
                    }
                    return role;
                }
                return element;
            }
        });
    }
}
