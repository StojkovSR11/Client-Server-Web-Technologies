package com.example.SVT_KVT.model;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    private LocalDate date;  // Changed from LocalDateTime to LocalDate

    @Column(nullable = false)
    private LocalTime fromTime;

    @Column(nullable = false)
    private LocalTime untilTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @JsonProperty("userId")
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
    @JsonIgnore
    private Facility facility;

    @JsonProperty("facilityId")
    public Integer getFacilityId() {
        return facility != null ? facility.getId() : null;
    }

    public void setFacilityId(Integer facilityId) {
        if (this.facility == null) {
            this.facility = new Facility();
        }
        this.facility.setId(facilityId);
    }

    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = true)
    @JsonIgnore
    private Discipline discipline;

    @JsonProperty("disciplineId")
    public Integer getDisciplineId() {
        return discipline != null ? discipline.getId() : null;
    }

    public void setDisciplineId(Integer disciplineId) {
        if (this.discipline == null) {
            this.discipline = new Discipline();
        }
        this.discipline.setId(disciplineId);
    }

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

    public LocalDate getDate() {  // Changed return type to LocalDate
        return date;
    }

    public void setDate(LocalDate date) {  // Changed parameter type to LocalDate
        this.date = date;
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


