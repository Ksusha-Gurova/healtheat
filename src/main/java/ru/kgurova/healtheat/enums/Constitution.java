package ru.kgurova.healtheat.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Constitution
 * Типы телослодения
 *
 * @author kgurova
 */
@Getter
@RequiredArgsConstructor
public enum Constitution {
  ASTHENIC("Астеник"),
  ECTOMORPH("Эктоморф"),
  NORMOSTHINIC("Нормостеник"),
  MESOMORPH("Мезоморф"),
  HYPERSTHENIC("Гиперстеник"),
  ENDOMORPH("Эндоморф"),
  LOT_OF_MUSCLE("Сильно развита мышечная масса");

  private final String rusValue;
}
