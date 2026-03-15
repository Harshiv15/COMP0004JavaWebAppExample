package uk.ac.ucl;

import java.util.Map;

public class PatientHelpers {
  public static String truncateName(String name) {
    for(int i = 0; i < name.length(); i++) {
      if(Character.isDigit(name.charAt(i))) {
        return name.substring(0, i);
      }
    }
    return name;
  }

  public static String formatPatientInfo(Map<String, String> data) {
    return data.get("PREFIX") + " " +
        truncateName(data.get("FIRST")) + " " +
        truncateName(data.get("LAST"));
  }
}