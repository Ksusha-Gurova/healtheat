package ru.kgurova.healtheat.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kgurova.healtheat.dto.CalculationsResponse;
import ru.kgurova.healtheat.dto.PersonDataRequest;
import ru.kgurova.healtheat.service.Calculate;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CalculationResource {

  private final Calculate calculate;

  @PostMapping("/calculate")
  public ResponseEntity<CalculationsResponse> getCalculations(PersonDataRequest personalData) {
    return ResponseEntity.ok(calculate.getCalculations(personalData));
  }

}
