package com.livre.controller;

import com.livre.model.Livre;
import com.livre.service.LivreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
public class LivreRestController {

    private final LivreService livreService;

    public LivreRestController(LivreService livreService) {
        this.livreService = livreService;
    }

    @GetMapping
    public List<Livre> getAllLivres() {
        return livreService.getAllLivres();
    }

    @GetMapping("/{id}")
    public Livre getLivre(@PathVariable Long id) {
        return livreService.getLivreById(id);
    }

    @PostMapping
    public Livre addLivre(@RequestBody Livre livre) {
        return livreService.saveLivre(livre);
    }

    @PutMapping("/{id}")
    public Livre updateLivre(@PathVariable Long id, @RequestBody Livre livre) {
        livre.setId(id);
        return livreService.saveLivre(livre);
    }

    @DeleteMapping("/{id}")
    public void deleteLivre(@PathVariable Long id) {
        livreService.deleteLivre(id);
    }
}
