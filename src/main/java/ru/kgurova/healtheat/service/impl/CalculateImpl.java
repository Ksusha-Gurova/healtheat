package ru.kgurova.healtheat.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kgurova.healtheat.dto.CalculationsResponse;
import ru.kgurova.healtheat.dto.Day;
import ru.kgurova.healtheat.dto.Meal;
import ru.kgurova.healtheat.dto.PersonDataRequest;
import ru.kgurova.healtheat.enums.BodyMassIndex;
import ru.kgurova.healtheat.enums.Gender;
import ru.kgurova.healtheat.enums.GoalCalculating;
import ru.kgurova.healtheat.enums.MealType;
import ru.kgurova.healtheat.service.Calculate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalculateImpl implements Calculate {

  @Override
  public CalculationsResponse getCalculations(PersonDataRequest personalData) {
    double bodyMassIndex = calculateBodyMassIndex(personalData.getWeight(), personalData.getHeight());
    BodyMassIndex bodyMassIndexType = BodyMassIndex.getByIndex(bodyMassIndex);
    validateDesiredWeight(personalData, bodyMassIndexType);
    double idealWeight = Optional.ofNullable(personalData.getConstitution())
        .map(cons -> calculateIdealWeightWithConstitution(personalData))
        .orElse(calculateIdealWeight(personalData));
    Day day = new Day();
    calculateDailyCalorieRequirement(personalData, day);
    distributeMacronutrientStandards(day);
    distributeMacronutrientsAndCaloriesBetweenMeals(day);

    return new CalculationsResponse()
        .setBodyMassIndex(bodyMassIndex)
        .setBodyMassIndexType(bodyMassIndexType)
        .setIdealWeight(idealWeight)
        .setDay(day);
  }

  /**
   * calculateIdealWeight
   * Расчет идеального веса
   *
   * @author kgurova
   */
  private double calculateIdealWeight(PersonDataRequest personalData) {
    double result = 0;
    if (personalData.getHeight() <= 165) {
      result = personalData.getHeight() - 100;
    } else if (personalData.getHeight() <= 175) {
      result = personalData.getHeight() - 105;
    } else {
      result = personalData.getHeight() - 110;
    }
    return result;
  }

  /**
   * addConstitutionToCalculateIdealWeight
   * Учитывать тип телосложения при расчете идеального веса
   *
   * @author kgurova
   */
  private double calculateIdealWeightWithConstitution(PersonDataRequest personalData) {
    double idealWeight = calculateIdealWeight(personalData);
    return switch (personalData.getConstitution()) {
      case ASTHENIC, ECTOMORPH -> idealWeight * 0.9;
      case NORMOSTHINIC, MESOMORPH -> idealWeight;
      case HYPERSTHENIC, ENDOMORPH -> idealWeight * 1.1;
      case LOT_OF_MUSCLE -> idealWeight * 1.3;
    };
  }

  /**
   * calculateDailyCalorieRequirement
   * Расчет суточной потребности в каллориях
   *
   * @author kgurova
   */
  private void calculateDailyCalorieRequirement(PersonDataRequest personData, Day day) {
    if (personData.getGoalCalculating().equals(GoalCalculating.WEIGHT_LOSS) && personData.getWeight() - personData.getDesiredWeight() >= 10) {
      day.setDailyCalories(calculateBasicMetabolism(personData) * personData.getPhysicalActivityRatio().getRatio() - 300);
    } else {
      day.setDailyCalories(
          calculateBasicMetabolism(personData) * personData.getPhysicalActivityRatio().getRatio());
    }
  }

  private double calculateBasicMetabolism(PersonDataRequest personData) {
    double result = 0.0;
    if (personData.getGoalCalculating().equals(GoalCalculating.WEIGHT_LOSS) && personData.getWeight() - personData.getDesiredWeight() < 10) {
      result = calculateBasicMetabolism(personData.getDesiredWeight(), personData.getHeight(), personData.getAge(), personData.getGender());
    } else {
      result = calculateBasicMetabolism(personData.getWeight(), personData.getHeight(), personData.getAge(), personData.getGender());
    }
    return result;
  }

  /**
   * calculateBasicMetabolism
   * Расчет величины основного обмена веществ
   *
   * @author kgurova
   */
  private double calculateBasicMetabolism(Double weight, Integer height, Integer age, Gender gender) {
    double result = 9.99 * weight + 6.25 * height - 4.92 * age;
    return switch (gender) {
      case MALE -> result + 5;
      case FEMALE -> result - 161;
    };
  }

  private void validateDesiredWeight(PersonDataRequest personalData, BodyMassIndex bodyMassIndex) {
    if (personalData.getGoalCalculating().equals(GoalCalculating.WEIGHT_LOSS)
        && personalData.getWeight() - personalData.getDesiredWeight() < 10
        && BodyMassIndex.NORMAL.equals(bodyMassIndex)) {
      throw new IllegalArgumentException("Желаемый вес меньше нормы относительно роста");
    }
  }

  /**
   * calculateBodyMassIndex
   * Расчет индекса массы тела
   *
   * @author kgurova
   */
  private double calculateBodyMassIndex(double weight, double height) {
    return weight / Math.pow(height / 100, 2);
  }

  /**
   * distributeMacronutrientStandards
   * Распределение каллорий помакронутриентам
   *
   * @author kgurova
   */
  private void distributeMacronutrientStandards(Day day) {
    day.setDailyProteins(day.getDailyCalories() * 0.2 / 4);
    day.setDailyFats(day.getDailyCalories() * 0.3 / 9);
    day.setDailyCarbohydrates(day.getDailyCalories() * 0.5 / 4);
  }

  /**
   * distributeMacronutrientsAndCaloriesBetweenMeals
   * Распределение макронутриентов и каллорий по приемам пищи
   *
   * @author kgurova
   */
  private void distributeMacronutrientsAndCaloriesBetweenMeals(Day day) {
    List<Meal> meals = Arrays.stream(MealType.values())
        .map(type -> new Meal()
            .setMealType(type)
            .setCalories(day.getDailyCalories() * type.getProportionOfDailyCalories())
            .setProteins(day.getDailyProteins() * type.getProportionOfDailyCalories())
            .setFats(day.getDailyFats() * type.getProportionOfDailyCalories())
            .setCarbohydrates(day.getDailyCarbohydrates() * type.getProportionOfDailyCalories())
        ).toList();
    day.setMeals(meals);
  }

}
