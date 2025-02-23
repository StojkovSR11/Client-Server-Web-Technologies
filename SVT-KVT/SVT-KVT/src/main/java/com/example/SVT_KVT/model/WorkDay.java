package com.example.SVT_KVT.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class WorkDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate validFrom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Day day;

    @Column(nullable = false)
    private LocalTime fromTime;

    @Column(nullable = false)
    private LocalTime untilTime;

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = true)
    @JsonIgnore
    private Facility facility;

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
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

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }
    public static DayOfWeek toDayOfWeek(Day day) {
        switch (day) {
            case MONDAY: return DayOfWeek.MONDAY;
            case TUESDAY: return DayOfWeek.TUESDAY;
            case WEDNESDAY: return DayOfWeek.WEDNESDAY;
            case THURSDAY: return DayOfWeek.THURSDAY;
            case FRIDAY: return DayOfWeek.FRIDAY;
            case SATURDAY: return DayOfWeek.SATURDAY;
            case SUNDAY: return DayOfWeek.SUNDAY;
            default: throw new IllegalArgumentException("Unknown Day: " + day);
        }
    }

    public static Day toWorkDay(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY: return Day.MONDAY;
            case TUESDAY: return Day.TUESDAY;
            case WEDNESDAY: return Day.WEDNESDAY;
            case THURSDAY: return Day.THURSDAY;
            case FRIDAY: return Day.FRIDAY;
            case SATURDAY: return Day.SATURDAY;
            case SUNDAY: return Day.SUNDAY;
            default: throw new IllegalArgumentException("Unknown DayOfWeek: " + dayOfWeek);
        }
    }

}


