package com.elife.sprotscape.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class imageStade {
  public imageStade(String name,String type,byte[] picbyte,Stade stade){
    this.NomPhotoimageStade=name;
    this.typePhotoimageStade=type;
    this.PhotoimageStade=picbyte;
    this.Stade=stade;
  }
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long idImageStade;
  private String NomPhotoimageStade;
  private String typePhotoimageStade;
  @Column(length = 50000000)
  private byte[] PhotoimageStade;
  @ManyToOne
  @JoinColumn(name = "Stade")
  private Stade Stade;
}
