package com.elife.sprotscape.DAO.StadeRepository;

import com.elife.sprotscape.Entities.Activite;
import com.elife.sprotscape.Entities.Stade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.List;

public interface StadeRepository extends JpaRepository<Stade,Long> {
    Stade findByIdStadeAndActivite(Long id, Activite activite);
    List<Stade> findByVilleAndActivite(String ville, Activite activite);
}
