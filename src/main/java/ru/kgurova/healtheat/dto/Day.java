package ru.kgurova.healtheat.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Day {

  private double dailyCalories;
  private double dailyProteins;
  private double dailyFats;
  private double dailyCarbohydrates;
  private List<Meal> meals;

}
