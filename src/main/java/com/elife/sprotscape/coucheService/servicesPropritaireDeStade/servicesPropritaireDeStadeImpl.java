package com.elife.sprotscape.coucheService.servicesPropritaireDeStade;

import com.elife.sprotscape.DAO.PropritaireDeStadeRepository.PropritaireDeStadeRepository;
import com.elife.sprotscape.DTO.propritaireDeStadeDTO.propritaireDeStadeRequestDTO;
import com.elife.sprotscape.Entities.propritaireDeStade;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@AllArgsConstructor
@Service
@Transactional
public class servicesPropritaireDeStadeImpl implements servicesPropritaireDeStade {
  private JdbcUserDetailsManager jdbcUserDetailsManager;
  private PasswordEncoder passwordEncoder;
  private PropritaireDeStadeRepository PropritaireDeStadeRepository;
  @Override
  public void ajouter_un_PropritaireDeStade(String propritaireDeStadeRequestDTO, MultipartFile File) throws IOException {
    propritaireDeStadeRequestDTO propritaireDeStadeRequestDTO1=new ObjectMapper().readValue(propritaireDeStadeRequestDTO,propritaireDeStadeRequestDTO.class);
    propritaireDeStade propritaireDeStade=new propritaireDeStade();
    propritaireDeStade.setMail(propritaireDeStadeRequestDTO1.getMail());
    propritaireDeStade.setNom(propritaireDeStadeRequestDTO1.getNom());
    propritaireDeStade.setPrenom(propritaireDeStadeRequestDTO1.getPrenom());
    propritaireDeStade.setTelephone(propritaireDeStadeRequestDTO1.getTelephone());
    propritaireDeStade.setEntreprise(propritaireDeStadeRequestDTO1.getEntrepriseee());
    propritaireDeStade.setActiviteSportive(propritaireDeStadeRequestDTO1.getActiviteSportive());
    propritaireDeStade.setNomPhotoPropritaire(File.getOriginalFilename());
    propritaireDeStade.setTypePhotoPropritaire(File.getContentType());
    propritaireDeStade.setPhotoPropritaire(compressBytes(File.getBytes()));
    propritaireDeStade.setCompteVerifier(false);
    jdbcUserDetailsManager.createUser(User.withUsername(propritaireDeStade.getEntreprise()).password(passwordEncoder.encode(propritaireDeStade.getEntreprise()
    )).authorities("PROPRITAIRE").build());
    PropritaireDeStadeRepository.save(propritaireDeStade);

  }

  @Override
  public propritaireDeStade getPropritaireDeStade(Long id) {
    return null;
  }

  @Override
  public List<propritaireDeStade> getToutPropritaireDeStade() {
    return null;
  }

  @Override
  public void modifierPropritaireDeStade(propritaireDeStadeRequestDTO propritaireDeStadeRequestDTO) {

  }

  @Override
  public void deletePropritaireDeStade(Long id) {

  }


  public static byte[] compressBytes(byte[] data) {
    Deflater deflater = new Deflater();
    deflater.setInput(data);
    deflater.finish();

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    byte[] buffer = new byte[1024];
    while (!deflater.finished()) {
      int count = deflater.deflate(buffer);
      outputStream.write(buffer, 0, count);
    }
    try {
      outputStream.close();
    } catch (IOException e) {
    }
    System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

    return outputStream.toByteArray();
  }
  public static byte[] decompressBytes(byte[] data) {
    Inflater inflater = new Inflater();
    inflater.setInput(data);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    byte[] buffer = new byte[1024];
    try {
      while (!inflater.finished()) {
        int count = inflater.inflate(buffer);
        outputStream.write(buffer, 0, count);
      }
      outputStream.close();
    } catch (IOException ioe) {
    } catch (DataFormatException e) {
    }
    return outputStream.toByteArray();
  }
}
