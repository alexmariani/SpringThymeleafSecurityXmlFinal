package it.jdk.hibernatespringthymelearfteam.controller;


import it.jdk.hibernatespringthymelearfteam.business.FanBO;
import it.jdk.hibernatespringthymelearfteam.business.PlayerBO;
import it.jdk.hibernatespringthymelearfteam.business.TeamBO;
import it.jdk.hibernatespringthymelearfteam.domain.Player;
import it.jdk.hibernatespringthymelearfteam.utils.ClientResponse;
import java.security.Principal;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Alex
 */
@RequestMapping("/player")
@Controller
public class PlayerController {

    @Autowired
    private PlayerBO playerbo;

    @Autowired
    private TeamBO teambo;

    @Autowired
    private FanBO fanbo;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    private static final Logger logger = Logger.getLogger(PlayerController.class);

    @ModelAttribute
    protected void LoadAttributes(Model model) {
        logger.debug("LoadAttributes method invoked()");
        model.addAttribute("teams", teambo.findAllTeams());
        model.addAttribute("fans", fanbo.findAllFans());
    }

    @GetMapping({"/", "/home"})
    public ModelAndView home(@RequestHeader("user-agent") 
            String userAgent, @CookieValue("JSESSIONID") String cookie) {

        ModelAndView mav = new ModelAndView("views/player", "newPlayer", new Player());
        mav.addObject("players", playerbo.findAllPlayers());

        return mav;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("newPlayer") Player player,
            BindingResult br, final RedirectAttributes redirectAttributes,
            HttpServletRequest hsr) {

        logger.debug("Player Save");
        System.out.println(br.hasErrors());
        System.out.println(br.hasFieldErrors());
        if (player.getId() != null) {
            return new ModelAndView("redirect:/player/update");
        }
        if (br.hasErrors()) {
            return new ModelAndView("views/player", "newPlayer", player);
        } else {

            try {
                playerbo.save(player);
                redirectAttributes.addFlashAttribute("response", new ClientResponse(true, messageSource
                        .getMessage("feedback.messages.success", null, localeResolver.resolveLocale(hsr))));
            } catch (DataAccessException e) {
                logger.error(e.toString());
                redirectAttributes.addFlashAttribute("response",
                        new ClientResponse(false, e.getMessage()));
            }
            return new ModelAndView("redirect:/player/home");
        }
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("newPlayer") @Valid Player player,
            BindingResult br, final RedirectAttributes redirectAttributes,
            HttpServletRequest hsr, @RequestParam Long idcheck) {
        System.out.println(idcheck);
        player.setId(idcheck);
        if(player.getId()==null){
            return new ModelAndView("redirect:/player/save");
        }
        if (br.hasErrors()) {
            return new ModelAndView("views/player", "newPlayer", player);
        } else {
            try {
                playerbo.update(player);
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
        return new ModelAndView("redirect:/player/home");
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id,
            final RedirectAttributes redirectAttributes, HttpServletRequest hsr) {
        logger.debug("Delete Player invoked()");

        try {
            playerbo.delete(id);
            redirectAttributes.addFlashAttribute("response",
                    new ClientResponse(true, messageSource
                            .getMessage("feedback.messages.success", null,
                                    localeResolver.resolveLocale(hsr))));
        } catch (DataAccessException ex) {
            logger.error(ex.toString());
            redirectAttributes.addFlashAttribute("response",
                    new ClientResponse(false, ex.getMessage()));
        }

        return "redirect:/player/home";
    }
}
