package ru.kgurova.healtheat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * BodyMassIndex
 * Градация индекса масы тела
 *
 * @author kgurova
 */
@Getter
@AllArgsConstructor
public enum BodyMassIndex {
  PRONOUNCED_DEFICIENCY("Выраженный дефицит массы тела", 0.1, 16.0),
  DEFICIENCY("Недостаточная (дефицит) масса тела", 16.0, 18.5),
  NORMAL("Норма", 18.5, 25.0),
  OVERWEIGHT("Избыточная масса тела", 25.0, 30.0),
  OBESITY_ONE("Ожирение 1 степени", 30.0, 35.0),
  OBESITY_TWO("Ожирение 2 степени", 35.0, 40.0),
  OBESITY_THREE("Ожирение 3 степени", 40.0, 9999.0);

  private final String description;
  private final double lowFrontier;
  private final double highFrontier;

  public static boolean isNormal(double index) {
    return NORMAL.getLowFrontier() <= index && index < NORMAL.getHighFrontier();
  }

  public static BodyMassIndex getByIndex(double index) {
    BodyMassIndex result = null;
    for (BodyMassIndex bodyMassIndex : BodyMassIndex.values()) {
      if (bodyMassIndex.getLowFrontier() <= index && index < bodyMassIndex.getHighFrontier()) {
        result = bodyMassIndex;
      }
    }
    return result;
  }
}
