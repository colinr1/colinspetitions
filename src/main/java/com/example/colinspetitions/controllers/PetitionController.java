package com.example.colinspetitions.controllers;

import com.example.colinspetitions.models.Petition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PetitionController {
    private List<Petition> petitions = new ArrayList<>();

    public PetitionController() {
        petitions.add(new Petition("Save the Forest", "A petition to save the local forest from logging."));
        petitions.add(new Petition("Increase School Funding", "A petition to increase funding for local schools."));
    }

    @GetMapping("/create")
    public String showCreatePetitionForm(Model model) {
        model.addAttribute("petition", new Petition("", ""));
        return "createPetition";
    }

    @PostMapping("/create")
    public String createPetition(@ModelAttribute Petition petition) {
        petitions.add(petition);
        return "redirect:/petitions";
    }

    @GetMapping("/petitions")
    public String viewAllPetitions(Model model) {
        model.addAttribute("petitions", petitions);
        return "viewPetitions";
    }

    @GetMapping("/petition/{id}")
    public String viewPetition(@PathVariable int id, Model model) {
        Optional<Petition> petition = petitions.stream().filter(p -> p.getId() == id).findFirst();
        petition.ifPresent(value -> model.addAttribute("petition", value));
        return petition.isPresent() ? "petitionDetails" : "redirect:/petitions";
    }

    @PostMapping("/petition/{id}/sign")
    public String signPetition(@PathVariable int id, @RequestParam String name, @RequestParam String email) {
        Optional<Petition> petition = petitions.stream().filter(p -> p.getId() == id).findFirst();
        petition.ifPresent(p -> p.addSignature(name, email));
        return "redirect:/petition/" + id;
    }
}
