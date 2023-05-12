package com.example.pstravel.Entity;

import com.example.pstravel.Entity.timeTable.TimeTable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
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

    @Builder
    public TravelPlan(Long tavelPlanIdx, Long userInformIdx, String title, String contents) {
        this.tavelPlanIdx = tavelPlanIdx;
        this.userInformIdx = userInformIdx;
        this.title = title;
        this.contents = contents;
    }
}
