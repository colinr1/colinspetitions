package com.example.colinspetitions.controllers;

import com.example.colinspetitions.models.Petition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

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
}
