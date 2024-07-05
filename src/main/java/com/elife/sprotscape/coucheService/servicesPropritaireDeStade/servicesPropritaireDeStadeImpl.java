package com.elife.sprotscape.coucheService.servicesPropritaireDeStade;

import com.elife.sprotscape.DAO.PropritaireDeStadeRepository.PropritaireDeStadeRepository;
import com.elife.sprotscape.DTO.propritaireDeStadeDTO.propritaireDeStadeRequestDTO;
import com.elife.sprotscape.Entities.propritaireDeStade;
import com.elife.sprotscape.coucheService.serviceMail.SendMailMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
  @Autowired
  private SendMailMessage sendMailMessage;
  @Override
  public void ajouter_un_PropritaireDeStade(String propritaireDeStadeRequestDTO, MultipartFile File) throws IOException, MessagingException {
    propritaireDeStadeRequestDTO propritaireDeStadeRequestDTO1=new ObjectMapper().readValue(propritaireDeStadeRequestDTO,propritaireDeStadeRequestDTO.class);
    propritaireDeStade propritaireDeStade=new propritaireDeStade();
    propritaireDeStade.setMail(propritaireDeStadeRequestDTO1.getMail());
    propritaireDeStade.setNom(propritaireDeStadeRequestDTO1.getNom());
    propritaireDeStade.setPrenom(propritaireDeStadeRequestDTO1.getPrenom());
    propritaireDeStade.setTelephone(propritaireDeStadeRequestDTO1.getTelephone());
    propritaireDeStade.setEntrepriseee(propritaireDeStadeRequestDTO1.getEntrepriseee());
    propritaireDeStade.setActiviteSportive(propritaireDeStadeRequestDTO1.getActiviteSportive());
    propritaireDeStade.setNomPhotoPropritaire(File.getOriginalFilename());
    propritaireDeStade.setTypePhotoPropritaire(File.getContentType());
    propritaireDeStade.setPhotoPropritaire(compressBytes(File.getBytes()));
    propritaireDeStade.setCompteVerifier(false);
    propritaireDeStade.setCode(RandomCodeGenerator());
    jdbcUserDetailsManager.createUser(User.withUsername(propritaireDeStade.getEntrepriseee()).password(passwordEncoder.encode(propritaireDeStade.getEntrepriseee()
    )).authorities("PROPRITAIRE").build());
    PropritaireDeStadeRepository.save(propritaireDeStade);
    sendMailMessage.sendMailToClient(propritaireDeStade.getMail(),"Félicitations M."+propritaireDeStade.getNom()+" "+propritaireDeStade.getPrenom()+", votre compte a été créé avec succès. Votre nom d'utilisateur est "+propritaireDeStade.getEntrepriseee()+"' et votre mot de passe est également "+propritaireDeStade.getEntrepriseee()+". De plus, un code pour créer des stades est le suivant :"+ propritaireDeStade.getCode(),"creation de compte SPORTSCAPE");

  }

  @Override
  public propritaireDeStade getPropritaireDeStade(String id) {
    propritaireDeStade propritaireDeStade=PropritaireDeStadeRepository.findByEntrepriseee(id);
    propritaireDeStade propritaireDeStade2=new propritaireDeStade();
    propritaireDeStade2=(new propritaireDeStade(propritaireDeStade.getNomPhotoPropritaire(),propritaireDeStade.getTypePhotoPropritaire(),decompressBytes(propritaireDeStade.getPhotoPropritaire())));


    return propritaireDeStade2;
  }
  @Override
  public void VerifierCompte(String id) {
    propritaireDeStade propritaireDeStade=PropritaireDeStadeRepository.findByEntrepriseee(id);
    propritaireDeStade.setCompteVerifier(true);

  }

  @Override
  public List<propritaireDeStade> getToutPropritaireDeStade() {
    List<propritaireDeStade>  propritaireDeStade=PropritaireDeStadeRepository.findAll();
    List<propritaireDeStade> imgPromo2=new ArrayList<>();
    for(int i=0;i<propritaireDeStade.size();i++)
    {
      imgPromo2.add(new propritaireDeStade(propritaireDeStade.get(i).getIdPropritaire(),
        propritaireDeStade.get(i).getNom(),
        propritaireDeStade.get(i).getPrenom(),
        propritaireDeStade.get(i).getTelephone(),
        propritaireDeStade.get(i).getMail(),
        propritaireDeStade.get(i).getActiviteSportive(),
        propritaireDeStade.get(i).getEntrepriseee(),
        propritaireDeStade.get(i).getNomPhotoPropritaire(),
        propritaireDeStade.get(i).getCode(),
        propritaireDeStade.get(i).getTypePhotoPropritaire(),
        decompressBytes(propritaireDeStade.get(i).getPhotoPropritaire()),propritaireDeStade.get(i).getCompteVerifier() ));



    }
    return imgPromo2;


  }

  @Override
  public void modifierPropritaireDeStade(propritaireDeStadeRequestDTO propritaireDeStadeRequestDTO) {

  }

  @Override
  public void deletePropritaireDeStade(Long id) {

  }
  @Override
  public long nombreDecompteVerifier(){
    return PropritaireDeStadeRepository.countByCompteVerifier(true);
  };
  @Override
  public long nombreDecompteNonVerifier(){
    return PropritaireDeStadeRepository.countByCompteVerifier(false);
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
  public static String RandomCodeGenerator(){
    Random random = new Random();
    int code = random.nextInt(100000);
    String codeStr = String.format("%05d", code);
    return codeStr;
  }
}
