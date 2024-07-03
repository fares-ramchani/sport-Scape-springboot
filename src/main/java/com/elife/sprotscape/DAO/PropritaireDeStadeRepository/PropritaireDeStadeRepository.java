package com.elife.sprotscape.DAO.PropritaireDeStadeRepository;

import com.elife.sprotscape.Entities.propritaireDeStade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PropritaireDeStadeRepository extends JpaRepository<propritaireDeStade,Long> {
  long countByCompteVerifier(boolean compteVerifier);
  propritaireDeStade findByCode(String code);
  propritaireDeStade findByEntrepriseee(String id);



}
