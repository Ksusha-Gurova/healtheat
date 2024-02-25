package ru.kgurova.healtheat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GoalCalculating
 * Цель расчетов
 *
 * @author kgurova
 */
@Getter
@AllArgsConstructor
public enum GoalCalculating {

  WEIGHT_LOSS("Потеря веса"),
  MAINTENANCE_WEIGHT("Поддержание веса");

  private final String description;
}
