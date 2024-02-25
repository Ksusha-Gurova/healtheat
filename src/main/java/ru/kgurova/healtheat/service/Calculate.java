package ru.kgurova.healtheat.service;

import ru.kgurova.healtheat.dto.CalculationsResponse;
import ru.kgurova.healtheat.dto.PersonDataRequest;

public interface Calculate {

  CalculationsResponse getCalculations(PersonDataRequest personalData);

}
