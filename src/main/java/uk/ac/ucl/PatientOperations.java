package uk.ac.ucl;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.DAYS;
import static uk.ac.ucl.PatientHelpers.formatPatientInfo;

public class PatientOperations {
  public String oldestPatient(DataFrame df) {
    Column birthdates = df.getColumn("BIRTHDATE");
    Column deathdates = df.getColumn("DEATHDATE");

    int oldestIndex = 0;
    long maxAge = 0L;

    for(int i = 0; i < df.getRowCount(); i++) {
      LocalDate birthdate = LocalDate.parse(birthdates.getRowValue(i));
      LocalDate enddate;

      if(deathdates.getRowValue(i).isEmpty()) enddate = LocalDate.now();
      else enddate = LocalDate.parse(deathdates.getRowValue(i));

      var age = DAYS.between(birthdate, enddate);

      if(age > maxAge) {
        maxAge = age;
        oldestIndex = i;
      }
    }

    return formatPatientInfo(df.getRow(oldestIndex));
  }

  public Map<String, Integer> patientsPerPlace(DataFrame df) {
    HashMap<String, Integer> map = new HashMap<>();
    Column cities = df.getColumn("CITY");

    for(int i = 0; i < df.getRowCount(); i++) {
      String city = cities.getRowValue(i);
      map.put(city, map.getOrDefault(city, 0) + 1);
    }

    return map;
  }

  public Map<String, Integer> patientsWithSameBirthday(DataFrame df) {
    Column birthdates = df.getColumn("BIRTHDATE");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d");

    Map<String, Integer> map = IntStream.range(0, df.getRowCount())
        .mapToObj(i -> LocalDate.parse(birthdates.getRowValue(i)))
        .map(birthdate -> MonthDay.from(birthdate).format(formatter))
        .collect(Collectors.toMap(formatted -> formatted, _ -> 1, Integer::sum));

    map.entrySet().removeIf(e -> e.getValue() == 1);
    return map;
  }
}
