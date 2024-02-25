package ru.kgurova.healtheat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.kgurova.healtheat.enums.MealType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Meal {

  private MealType mealType;
  private double calories;
  private double proteins;
  private double fats;
  private double carbohydrates;

}
