package com.elife.sprotscape.DAO.AthleteRepository;

import com.elife.sprotscape.Entities.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete,Long> {
}
