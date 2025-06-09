package com.livre.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.livre.model.Livre;
import com.livre.service.LivreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livres")
public class LivreController {
    private static final Logger logger = LoggerFactory.getLogger(LivreController.class);
    private final LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @GetMapping
    public String listLivres(Model model) {
        model.addAttribute("livres", livreService.getAllLivres());
        return "livres";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("livre", new Livre());
        return "form-livre";
    }

    @PostMapping
    public String saveLivre(@ModelAttribute("livre") Livre livre, Model model) {
        try {
            livreService.saveLivre(livre);
            return "redirect:/livres";
        } catch (Exception e) {
            logger.error("Error saving book", e);
            model.addAttribute("error", "Error saving book. Please try again.");
            return "form-livre";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Livre livre = livreService.getLivreById(id);
        if (livre == null) {
            return "redirect:/livres";
        }
        model.addAttribute("livre", livre);
        return "form-livre";
    }

    @GetMapping("/delete/{id}")
    public String deleteLivre(@PathVariable Long id) {
        try {
            livreService.deleteLivre(id);
        } catch (Exception e) {
            logger.error("Error deleting book", e);
        }
        return "redirect:/livres";
    }
}
