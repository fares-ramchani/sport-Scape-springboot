package com.elife.sprotscape.coucheService.serviceEvenement;

import java.util.List;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import com.elife.sprotscape.DAO.EvenementRepository.EvenementRepository;
import com.elife.sprotscape.DTO.ActiviteDTO.ActiviteRequestDTO;
import com.elife.sprotscape.DTO.EvenementDTO.EvenementRequestDTO;
import com.elife.sprotscape.Entities.Evenement;
import com.elife.sprotscape.enums.Estpayer;
import com.elife.sprotscape.enums.Etat;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class serviceEvenementImpl implements serviceEvenement {
    private EvenementRepository evenementRepository;

    @Override
    public void addEvenement(String evenementRequestDTO, MultipartFile File) throws IOException {
        EvenementRequestDTO evenementRequestDTO1 = new ObjectMapper().readValue(evenementRequestDTO,
                EvenementRequestDTO.class);
        Evenement evenement = new Evenement();
        evenement.setNomEvenement(evenementRequestDTO1.getNomEvenement());
        evenement.setDescription(evenementRequestDTO1.getDescription());
        evenement.setLieu(evenementRequestDTO1.getLieu());
        evenement.setPrix(evenementRequestDTO1.getPrix());
        evenement.setNombreParticipant(evenementRequestDTO1.getNombreParticipant());
        evenement.setEtatEvenement(Etat.NON_TRAITER);
        evenement.setEstPayer(Estpayer.NON);
        evenement.setPhotoEvenement(compressBytes(File.getBytes()));
        evenement.setTypephotoEvenement(File.getContentType());
        evenement.setNomphotoEvenement(File.getOriginalFilename());
        evenementRepository.save(evenement);
    }

    @Override
    public Evenement getEvenementById(Long id) {
        return evenementRepository.findById(id).get();
    }

    @Override
    public List<Evenement> getAllEvenement() {
        return evenementRepository.findAll();
    }

    @Override
    public Evenement findByNomEvenement(String name) {
        return evenementRepository.findByNomEvenement(name);
    }

    @SuppressWarnings("unlikely-arg-type")
    @Override
    public void updateEvenement(String evenementRequestDTO, Long id, MultipartFile File)
            throws IOException {
        EvenementRequestDTO evenementRequestDTO1 = new ObjectMapper().readValue(evenementRequestDTO,
                EvenementRequestDTO.class);
        Evenement evenement = evenementRepository.findById(id).get();

        if (evenementRequestDTO1.getNomEvenement() != "") {
            evenement.setNomEvenement(evenementRequestDTO1.getNomEvenement());
        }
        if (evenementRequestDTO1.getDescription() != "") {
            evenement.setDescription(evenementRequestDTO1.getDescription());
        }
        if (evenementRequestDTO1.getLieu() != "") {
            evenement.setLieu(evenementRequestDTO1.getLieu());
        }
        if (evenementRequestDTO1.getPrix() != -1) {
            evenement.setPrix(evenementRequestDTO1.getPrix());
        }
        if (evenementRequestDTO1.getNombreParticipant() != -1) {
            evenement.setNombreParticipant(evenementRequestDTO1.getNombreParticipant());
        }
            evenement.setEstPayer(Estpayer.NON);
        if (!evenementRequestDTO1.getEtatEvenement().equals("")) {
            evenement.setEtatEvenement(evenementRequestDTO1.getEtatEvenement());
        }
        if (compressBytes(File.getBytes()) != null) {
            evenement.setPhotoEvenement(compressBytes(File.getBytes()));
        }
        if (File.getContentType() != "") {
            evenement.setTypephotoEvenement(File.getContentType());
        }
        if (evenementRequestDTO1.getNomphotoEvenement() != "") {
            evenement.setNomphotoEvenement(evenementRequestDTO1.getNomphotoEvenement());
        }
        evenementRepository.save(evenement);
    }

    @Override
    public void deleteEvenement(Long id) {
        Evenement evenement = evenementRepository.findById(id).get();
        evenementRepository.delete(evenement);
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
