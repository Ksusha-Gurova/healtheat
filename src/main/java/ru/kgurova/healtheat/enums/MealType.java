package ru.kgurova.healtheat.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MealType {

  BREAKFAST("Завтрак", 0.25),
  FIRST_SNACK("Первый перекус", 0.15),
  LUNCH("Обед", 0.3),
  SECOND_SNACK("Второй перекус", 0.1),
  DINNER("Ужин", 0.2);

  private final String description;
  private final double proportionOfDailyCalories;
}
