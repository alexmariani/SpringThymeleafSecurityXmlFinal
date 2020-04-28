package it.jdk.hibernatespringthymelearfteam.controller;

import it.jdk.hibernatespringthymelearfteam.business.FanBO;
import it.jdk.hibernatespringthymelearfteam.business.PlayerBO;
import it.jdk.hibernatespringthymelearfteam.business.TeamBO;
import it.jdk.hibernatespringthymelearfteam.domain.Player;
import it.jdk.hibernatespringthymelearfteam.domain.Team;
import it.jdk.hibernatespringthymelearfteam.utils.ClientResponse;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
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
@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamBO teambo;

    @Autowired
    private PlayerBO playerbo;

    @Autowired
    private FanBO fanbo;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    static final Logger logger = Logger.getLogger(TeamController.class);

    @ModelAttribute
    protected void LoadDataAttribute(Model model) {
        logger.debug(playerbo);
        model.addAttribute("players", playerbo.findAllPlayers());
        model.addAttribute("fans", fanbo.findAllFans());
    }

    @GetMapping({"/", "/home"})
    public ModelAndView home() {
        logger.debug("Team Home");

        ModelAndView mav = new ModelAndView("views/team", "newTeam", new Team());
        Set<Team> teams = teambo.findAllTeams();
        mav.addObject("teams", teams);
        Set<Player> playersWT = playerbo.findAllPlayers();
        searchElement(playersWT);
        mav.addObject("playersWT", playersWT);
        logger.info(teams);
        return mav;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("newTeam") Team team,
            BindingResult br, final RedirectAttributes redirectAttributes,
            HttpServletRequest hsr) {

        logger.debug("Team Save");
        System.out.println(br.hasErrors());
        System.out.println(br.hasFieldErrors());
        
        if(team.getId()!=null){
            return new ModelAndView("redirect:/team/update");
        }
        if (br.hasErrors()) {
            return new ModelAndView("views/team", "newTeam", team);
        } else {

            try {
                teambo.save(team);
                redirectAttributes.addFlashAttribute("response", new ClientResponse(true, messageSource
                        .getMessage("feedback.messages.success", null, localeResolver.resolveLocale(hsr))));
            } catch (DataAccessException e) {
                logger.error(e.toString());
                redirectAttributes.addFlashAttribute("response",
                        new ClientResponse(false, e.getMessage()));
            }
            return new ModelAndView("redirect:/team/home");
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id,
            final RedirectAttributes redirectAttributes, HttpServletRequest hsr) {

        logger.debug("Delete Team invoked()");

        try {
            teambo.delete(id);
            redirectAttributes.addFlashAttribute("response",
                    new ClientResponse(true, messageSource
                            .getMessage("feedback.messages.success", null,
                                    localeResolver.resolveLocale(hsr))));
        } catch (DataAccessException ex) {
            logger.error(ex.toString());
            redirectAttributes.addFlashAttribute("response",
                    new ClientResponse(false, ex.getMessage()));
        }

        return "redirect:/team/home";
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("newTeam") @Valid Team team,
            BindingResult br, final RedirectAttributes redirectAttributes,
            HttpServletRequest hsr,@RequestParam Long idcheck) {

        System.out.println(idcheck);
        team.setId(idcheck);
        if (br.hasErrors()) {
            return new ModelAndView("views/team", "newTeam", team);
        } else {
            try {
                teambo.update(team);
                redirectAttributes.addFlashAttribute("response",
                        new ClientResponse(true, messageSource
                                .getMessage("feedback.messages.success", null,
                                        localeResolver.resolveLocale(hsr))));
            }catch(DataAccessException ex){
                logger.error(ex.toString());
                redirectAttributes.addFlashAttribute("response",
                    new ClientResponse(false, ex.getMessage()));
            }
        }
        return new ModelAndView("redirect:/team/home");

    }

    public void searchElement(Set<Player> playersWT) {

        Set<Player> deleted = new HashSet();
        playersWT.stream().filter((p) -> (p.getTeam() != null)).forEachOrdered((p) -> {
            deleted.add(p);
        });

        deleteElement(deleted, playersWT);
    }

    public void deleteElement(Set<Player> deleted, Set<Player> playersWT) {

        for (Player p : deleted) {
            playersWT.remove(p);
        }
    }

}
