package com.elife.sprotscape.coucheService.servicesAthlete;

import com.elife.sprotscape.DAO.AthleteRepository.AthleteRepository;
import com.elife.sprotscape.DTO.AthleteDTO.AthleteRequestDTO;
import com.elife.sprotscape.Entities.Athlete;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@AllArgsConstructor
@Service
@Transactional
public class servicesAthleteImpl implements servicesAthlete {
  private AthleteRepository AthleteRepository;
  private JdbcUserDetailsManager jdbcUserDetailsManager;
  private PasswordEncoder passwordEncoder;
  @Override
  public void ajouter_un_Athlete(AthleteRequestDTO AthleteRequestDTO) {
    Athlete Athlete=new Athlete();
    Athlete.setMail(AthleteRequestDTO.getMail());
    Athlete.setNom(AthleteRequestDTO.getNom());
    Athlete.setPrenom(AthleteRequestDTO.getPrenom());
    Athlete.setTelephone(AthleteRequestDTO.getTelephone());
    jdbcUserDetailsManager.createUser(User.withUsername(AthleteRequestDTO.getNom()).password(passwordEncoder.encode(AthleteRequestDTO.getMotDePasse()
    )).authorities("Athlete").build());

    AthleteRepository.save(Athlete);

  }


  @Override
  public Athlete getAthlete(Long id) {
    return AthleteRepository.findById(id).get() ;
  }

  @Override
  public List<Athlete> getToutAthlete() {
    return AthleteRepository.findAll() ;
  }

  @Override
  public void modifierAthlete(AthleteRequestDTO AthleteRequestDTO) {
    Athlete Athlete=AthleteRepository.findById(AthleteRequestDTO.getIdAthlete()).get();
    if (AthleteRequestDTO.getMail()!=""){
      Athlete.setMail(AthleteRequestDTO.getMail());
    }
    if (AthleteRequestDTO.getNom()!=""){
      Athlete.setNom(AthleteRequestDTO.getNom());
    }
    if (AthleteRequestDTO.getPrenom()!=""){
      Athlete.setPrenom(AthleteRequestDTO.getPrenom());
    }
    if (AthleteRequestDTO.getTelephone()!=""){
      Athlete.setTelephone(AthleteRequestDTO.getTelephone());
    }
    if (AthleteRequestDTO.getMotDePasse()!=""){
      jdbcUserDetailsManager.updateUser(User.withUsername(AthleteRequestDTO.getNom()).password(passwordEncoder.encode(AthleteRequestDTO.getMotDePasse()
      )).authorities("CLIENT").build());
    }
    AthleteRepository.save(Athlete);
  }



  @Override
  public void deleteAthlete(Long id) {
    Athlete Athlete=AthleteRepository.findById(id).get();
    AthleteRepository.delete(Athlete);
  }


}
