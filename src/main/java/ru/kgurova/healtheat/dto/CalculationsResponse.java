package ru.kgurova.healtheat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.kgurova.healtheat.enums.BodyMassIndex;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CalculationsResponse {

  private double bodyMassIndex;
  private BodyMassIndex bodyMassIndexType;
  private double idealWeight;
  private Day day;
}
