package com.example.colinspetitions.models;

import java.util.ArrayList;
import java.util.List;

public class Petition {
    private static int idCounter = 1;

    private int id;
    private String title;
    private String description;
    private List<String> signatures;

    public Petition(String title, String description) {
        this.id = idCounter++;
        this.title = title;
        this.description = description;
        this.signatures = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public List<String> getSignatures() { return signatures; }

    public void addSignature(String name, String email) {
        signatures.add(name + " (" + email + ")");
    }
}
