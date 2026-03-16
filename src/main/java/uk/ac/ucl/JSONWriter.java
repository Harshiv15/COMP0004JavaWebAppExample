package uk.ac.ucl;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class JSONWriter {
  public void writeJSON(DataFrame df, String filePath) {
    List<Map<String, String>> data = new ArrayList<>();
    List<String> columns = df.getColumnNames();

    for (int i = 0; i < df.getRowCount(); i++) {
      Map<String, String> row = new LinkedHashMap<>();

      for (String col : columns) row.put(col, df.getValue(col, i));

      data.add(row);
    }

    try {
      new ObjectMapper()
          .writerWithDefaultPrettyPrinter()
          .writeValue(new File(filePath), data);
    }
    catch (IOException e) {
      Logger.getLogger(JSONWriter.class.getName()).severe("Error writing to JSON file: " + e.getMessage());
    }
  }
}