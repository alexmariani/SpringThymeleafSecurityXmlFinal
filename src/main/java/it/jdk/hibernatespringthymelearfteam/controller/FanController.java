package it.jdk.hibernatespringthymelearfteam.controller;

import it.jdk.hibernatespringthymelearfteam.business.FanBO;
import it.jdk.hibernatespringthymelearfteam.business.PlayerBO;
import it.jdk.hibernatespringthymelearfteam.business.TeamBO;
import it.jdk.hibernatespringthymelearfteam.domain.Fan;
import it.jdk.hibernatespringthymelearfteam.domain.Player;
import it.jdk.hibernatespringthymelearfteam.domain.Team;
import it.jdk.hibernatespringthymelearfteam.utils.ClientResponse;
import java.security.Principal;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Alex
 */
@RequestMapping("/fan")
@Controller
public class FanController {

    @Autowired
    private FanBO fanbo;

    @Autowired
    private PlayerBO playerbo;

    @Autowired
    private TeamBO teambo;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    private static final Logger logger = Logger.getLogger(FanController.class);

    @ModelAttribute
    protected void LoadAttributes(Model model) {
        logger.info("LoadAttributes used()");
        model.addAttribute("players", playerbo.findAllPlayers());
        model.addAttribute("teams", teambo.findAllTeams());
    }

    @GetMapping({"/", "/home"})
    public ModelAndView home() {

        ModelAndView mav = new ModelAndView("views/fan", "newFan", new Fan());
        Set<Fan> fans=fanbo.findAllFans();
        mav.addObject("fans", fans);
        return mav;

    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("newFan") Fan fan,
            BindingResult br, final RedirectAttributes redirectAttributes,
            HttpServletRequest hsr) {

        logger.debug("Fan Save");
        System.out.println(br.hasErrors());
        System.out.println(br.hasFieldErrors());
        if (fan.getId() != null) {
            return new ModelAndView("redirect:/fan/update");
        }
        if (br.hasErrors()) {
            return new ModelAndView("views/fan", "newFan", fan);
        } else {

            try {
                fanbo.save(fan);
                redirectAttributes.addFlashAttribute("response", new ClientResponse(true, messageSource
                        .getMessage("feedback.messages.success", null, localeResolver.resolveLocale(hsr))));
            } catch (DataAccessException e) {
                logger.error(e.toString());
                redirectAttributes.addFlashAttribute("response",
                        new ClientResponse(false, e.getMessage()));
            }
            return new ModelAndView("redirect:/fan/home");
        }

    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("newFan") @Valid Fan fan,
            BindingResult br, final RedirectAttributes redirectAttributes,
            HttpServletRequest hsr, @RequestParam Long idcheck) {
        System.out.println(idcheck);
        fan.setId(idcheck);
        if (fan.getId() == null) {
            return new ModelAndView("redirect:/fan/save");
        }
        if (br.hasErrors()) {
            return new ModelAndView("views/fan", "newFan", fan);
        } else {
            try {
                fanbo.update(fan);
                redirectAttributes.addFlashAttribute("response",
                        new ClientResponse(true, messageSource
                                .getMessage("feedback.messages.success", null,
                                        localeResolver.resolveLocale(hsr))));
            } catch (DataAccessException ex) {
                logger.error(ex.toString());
                redirectAttributes.addFlashAttribute("response",
                        new ClientResponse(false, ex.getMessage()));
            }
        }
        return new ModelAndView("redirect:/fan/home");
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id,
            final RedirectAttributes redirectAttributes, HttpServletRequest hsr) {
        logger.debug("Delete Fan invoked()");

        try {
            fanbo.delete(id);
            redirectAttributes.addFlashAttribute("response",
                    new ClientResponse(true, messageSource
                            .getMessage("feedback.messages.success", null,
                                    localeResolver.resolveLocale(hsr))));
        } catch (DataAccessException ex) {
            logger.error(ex.toString());
            redirectAttributes.addFlashAttribute("response",
                    new ClientResponse(false, ex.getMessage()));
        }

        return "redirect:/fan/home";
    }

    
    @InitBinder("newFan")
    protected void initBinder(WebDataBinder binder) throws Exception {

        binder.registerCustomEditor(Set.class,
                "teams",
                new CustomCollectionEditor(Set.class) {
            @Override
            protected Object convertElement(Object element) {
                if (element instanceof String) {
                    String idStr = (String) element;
                    Team team = null;
                    try {
                        long id = Long.parseLong(idStr);
                        team = teambo.findByUid(id);
                    } catch (NumberFormatException nfe) {
                        System.out.println(nfe.getMessage());
                    }
                    return team;
                }
                return element;
            }

        });

        binder.registerCustomEditor(Set.class,
                "players",
                new CustomCollectionEditor(Set.class) {
            @Override
            protected Object convertElement(Object element) {

                if(element instanceof String){
                    String idStr=(String) element;
                    Player player=null;
                    
                    try{
                        long id=Long.parseLong(idStr);
                        player=playerbo.findByUid(id);
                    }catch(NumberFormatException nfe){
                        System.out.println(nfe.getMessage());
                    }
                    return player;
                }
                return element;
            }

        });

    }

}
