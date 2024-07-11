package com.elife.sprotscape.coucheService.serviceStade;

import com.elife.sprotscape.DAO.ActiviteRepository.ActiviteRepository;
import com.elife.sprotscape.DAO.ImageStadeRepository.ImageStadeRepository;
import com.elife.sprotscape.DAO.PropritaireDeStadeRepository.PropritaireDeStadeRepository;
import com.elife.sprotscape.DAO.StadeRepository.StadeRepository;
import com.elife.sprotscape.DTO.StadeDTO.stadeRequestDTO;
import com.elife.sprotscape.Entities.Activite;
import com.elife.sprotscape.Entities.Stade;
import com.elife.sprotscape.Entities.imageStade;
import com.elife.sprotscape.Entities.propritaireDeStade;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
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
public class serviceStadeImpl implements serviceStade {
  private StadeRepository StadeRepository;
  private ImageStadeRepository ImageStadeRepository;
  private ActiviteRepository ActiviteRepository;
  private PropritaireDeStadeRepository PropritaireDeStadeRepository;
  @Override
  public void ajouterStade(String stadeRequestDTO, MultipartFile image1, MultipartFile image2, MultipartFile image3, MultipartFile image4, MultipartFile image5) throws IOException {
    stadeRequestDTO stadeRequestDTO1=new ObjectMapper().readValue(stadeRequestDTO,stadeRequestDTO.class);
    Activite Activite=ActiviteRepository.findByNomActivite(stadeRequestDTO1.getActivite());
    propritaireDeStade propritaireDeStade=PropritaireDeStadeRepository.findByCode(stadeRequestDTO1.getCode());
    Stade stade=new Stade();
    stade.setNomStade(stadeRequestDTO1.getNom());
    stade.setTelephone(stadeRequestDTO1.getTelephone());
    stade.setAdresse(stadeRequestDTO1.getAdresse());
    stadeRequestDTO1.setVille(stadeRequestDTO1.getVille());
    stade.setEmail(stadeRequestDTO1.getMail());
    stade.setActivite(Activite);
    stade.setVille(stadeRequestDTO1.getVille());
    stade.setNombreDeJoueur(stadeRequestDTO1.getNombremax());
    stade.setDescription(stadeRequestDTO1.getDescription());
    stade.setDescriptionDetaillee(stadeRequestDTO1.getDescriptionDetaillee());
    stade.setActivite(Activite);
    stade.setVille(stadeRequestDTO1.getVille());
    stade.setCode(propritaireDeStade.getCode());
    stade.setPropritaireDeStade(propritaireDeStade);
    StadeRepository.save(stade);
    imageStade imagee1=new imageStade(image1.getOriginalFilename(),image1.getContentType(),compressBytes(image1.getBytes()),stade);
    ImageStadeRepository.save(imagee1);
    imageStade imagee2=new imageStade(image2.getOriginalFilename(),image2.getContentType(),compressBytes(image2.getBytes()),stade);
    ImageStadeRepository.save(imagee2);
    imageStade imagee3=new imageStade(image3.getOriginalFilename(),image3.getContentType(),compressBytes(image3.getBytes()),stade);
    ImageStadeRepository.save(imagee3);
    imageStade imagee4=new imageStade(image4.getOriginalFilename(),image4.getContentType(),compressBytes(image4.getBytes()),stade);
    ImageStadeRepository.save(imagee4);
    imageStade imagee5=new imageStade(image5.getOriginalFilename(),image5.getContentType(),compressBytes(image5.getBytes()),stade);
    ImageStadeRepository.save(imagee5);
  }
  @Override
  public List<Stade> getTousLesStade(){
    return StadeRepository.findAll() ;

  }

  @Override
  public Stade getStadeByIdAndActivite(Long id, String nomActivite) {
    Activite activite=ActiviteRepository.findByNomActivite(nomActivite);
    return StadeRepository.findByIdStadeAndActivite(id,activite);
  }

  @Override
  public Stade getStadeById(Long id) {
    return StadeRepository.findById(id).get();
  }

  @Override
  public List<Stade> getStadeByVilleAndActivite(String ville, String nomActivite) {
    Activite activite=ActiviteRepository.findByNomActivite(nomActivite);
    return StadeRepository.findByVilleAndActivite(ville,activite);
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



