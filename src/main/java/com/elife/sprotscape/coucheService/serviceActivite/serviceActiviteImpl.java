package com.elife.sprotscape.coucheService.serviceActivite;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.elife.sprotscape.DAO.ActiviteRepository.ActiviteRepository;
import com.elife.sprotscape.DTO.ActiviteDTO.ActiviteRequestDTO;
import com.elife.sprotscape.Entities.Activite;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class serviceActiviteImpl implements serviceActivite {
  private ActiviteRepository activiteRepository;

  @Override
  public void addActivite(String activiteRequestDTO, MultipartFile File) throws IOException {
    ActiviteRequestDTO activiteRequestDTO1 = new
    ObjectMapper().readValue(activiteRequestDTO, ActiviteRequestDTO.class);

    Activite activite = new Activite();
    activite.setNomActivite(activiteRequestDTO1.getNomActivite());
    activite.setPrix(activiteRequestDTO1.getPrix());
    activite.setActiviteRecommandee(activiteRequestDTO1.getActiviteRecommandee());
    activite.setPhotoActivite(compressBytes(File.getBytes()));
    activite.setTypephotoActivite(File.getContentType());
    activite.setNomphotoActivite(File.getOriginalFilename());
    activite.setDureeActivite(activiteRequestDTO1.getDureeActivite());
    activiteRepository.save(activite);
  }

  @Override
  public Activite getActiviteById(Long id) {
    return activiteRepository.findById(id).get();
  }

  @Override
  public List<Activite> getAllActivite() {
    return activiteRepository.findAll();
  }

  @Override
  public void updateActivite(String activiteRequestDTO, Long id, MultipartFile File) throws IOException {
    Activite activite = activiteRepository.findById(id).get();
    ActiviteRequestDTO activiteRequestDTO1 = new
    ObjectMapper().readValue(activiteRequestDTO, ActiviteRequestDTO.class);
    if (activiteRequestDTO1.getNomActivite() != "") {
      activite.setNomActivite(activiteRequestDTO1.getNomActivite());
    }
    if (activiteRequestDTO1.getPrix() != -1) {
      activite.setPrix(activiteRequestDTO1.getPrix());
    }
    if (activiteRequestDTO1.getActiviteRecommandee() != "") {
      activite.setActiviteRecommandee(activiteRequestDTO1.getActiviteRecommandee());
    }
    if (compressBytes(File.getBytes()) != null) {
      activite.setPhotoActivite(compressBytes(File.getBytes()));
    }
    if (File.getContentType() != "") {
      activite.setTypephotoActivite(File.getContentType());
    }
    if (activiteRequestDTO1.getNomphotoActivite() != "") {
      activite.setNomphotoActivite(activiteRequestDTO1.getNomphotoActivite());
    }
    if (activiteRequestDTO1.getNomActivite() != "") {
      activite.setDureeActivite(activiteRequestDTO1.getDureeActivite());
    }
    if (activiteRequestDTO1.getDureeActivite() != -1) {
      activite.setDureeActivite(activiteRequestDTO1.getDureeActivite());
    }
    activiteRepository.save(activite);
  }

  @Override
  public void deleteActivite(Long id) {
    Activite activite = activiteRepository.findById(id).get();
    activiteRepository.delete(activite);
  }

  @Override
  public Activite findByName(String name){
    return activiteRepository.findByName(name);
  }

  // compressing and decompressing files
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
