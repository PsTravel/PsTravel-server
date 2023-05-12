package com.example.pstravel.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class TravelPlan extends TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tavelPlanIdx;

    @Column(nullable = false)
    private Long userInformIdx;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;



}
