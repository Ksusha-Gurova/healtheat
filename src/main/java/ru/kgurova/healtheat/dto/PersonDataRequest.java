package ru.kgurova.healtheat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.kgurova.healtheat.enums.Constitution;
import ru.kgurova.healtheat.enums.Gender;
import ru.kgurova.healtheat.enums.GoalCalculating;
import ru.kgurova.healtheat.enums.PhysicalActivityRatio;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PersonDataRequest {

  private Gender gender;
  private Integer age;
  private Integer height;
  private Double weight;
  private Constitution constitution;
  private PhysicalActivityRatio physicalActivityRatio;
  private GoalCalculating goalCalculating;
  private Double desiredWeight;

}
