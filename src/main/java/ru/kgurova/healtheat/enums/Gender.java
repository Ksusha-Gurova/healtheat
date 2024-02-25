package ru.kgurova.healtheat.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Gender
 * Пол
 *
 * @author kgurova
 */
@Getter
@RequiredArgsConstructor
public enum Gender {

  MALE("мужской"),
  FEMALE("женской"),;

  private final String description;
}
