package ru.kgurova.healtheat.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * PhysicalActivityRatio
 * Коэффициент физической активности
 *
 * @author kgurova
 */
@Getter
@RequiredArgsConstructor
public enum PhysicalActivityRatio {

  EXTRA_LOW("Очень низкая физическая активность", 1.4),
  LOW("Низкая физическая активность", 1.6),
  MEDIUM("Средняя физическая активность", 1.9),
  HIGH("Высокая физическая активность", 2.2);

  private final String description;
  private final double ratio;
}
