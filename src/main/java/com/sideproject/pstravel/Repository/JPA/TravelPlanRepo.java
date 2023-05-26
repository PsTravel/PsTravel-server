package com.sideproject.pstravel.Repository.JPA;

import com.sideproject.pstravel.Entity.TravelPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPlanRepo extends JpaRepository<TravelPlan,Long> {
}
