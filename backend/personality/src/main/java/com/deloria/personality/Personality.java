package com.deloria.personality;

import jakarta.persistence.*;

@Entity
public class Personality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String artist;
    private String url;
    private String alt;

    public Personality() {}

    public Personality(String name, String description, String artist, String url, String alt) {
        this.name = name;
        this.description = description;
        this.artist = artist;
        this.url = url;
        this.alt = alt;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getArtist() { return artist; }  // Getter for artist
    public String getUrl() { return url; }        // Getter for image URL
    public String getAlt() { return alt; }        // Getter for alt text

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setArtist(String artist) { this.artist = artist; }  // Setter for artist
    public void setUrl(String url) { this.url = url; }              // Setter for image URL
    public void setAlt(String alt) { this.alt = alt; }              // Setter for alt text
}