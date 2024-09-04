package com.example.SVT_KVT.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private LocalTime fromTime;


    @Column(nullable = false)
    private LocalTime untilTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore // Prevent serialization of user to avoid circular references
    private User user;
    
    @JsonProperty("userId") // Serialize userId instead of the full User object
    public Integer getUserId() {
        return user != null ? user.getId() : null;
    }

    public void setUserId(Integer userId) {
        if (this.user == null) {
            this.user = new User();
        }
        this.user.setId(userId);
    }

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false)
    @JsonIgnore // Prevent serialization of facility
    private Facility facility;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "discipline_id", nullable = true)
    @JsonIgnore // Prevent serialization of discipline
    private Discipline discipline;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Image> images;

    // Getters and Setters
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalTime getUntilTime() {
        return untilTime;
    }

    public void setUntilTime(LocalTime untilTime) {
        this.untilTime = untilTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}

