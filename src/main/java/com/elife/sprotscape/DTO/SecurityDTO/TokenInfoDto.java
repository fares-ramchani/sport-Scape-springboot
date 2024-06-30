package com.elife.sprotscape.DTO.SecurityDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenInfoDto {
  private String accessToken;
  private String displayName;
  private String imageUrl;
}
