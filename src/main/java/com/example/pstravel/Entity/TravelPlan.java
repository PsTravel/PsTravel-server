package com.example.pstravel.Entity;

import com.example.pstravel.Entity.timeTable.TimeTable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter @NoArgsConstructor
public class TravelPlan extends TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tavelPlanIdx;

    @JoinColumn(name = "user_idx", referencedColumnName="userIdx", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User userIdx;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;


}
