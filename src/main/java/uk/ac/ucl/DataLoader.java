package uk.ac.ucl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.Iterator;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class DataLoader {
  public DataFrame readFile(String filePath) {
    DataFrame df = new DataFrame();

    try (CSVParser csvParser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT)) {
      Iterator<CSVRecord> iter = csvParser.iterator();
      if(!iter.hasNext()) return df;

      CSVRecord header = iter.next();
      for(String colName : header)
        df.addColumn(new Column(colName));

      while(iter.hasNext()) {
        CSVRecord record = iter.next();
        IntStream.range(0, record.size())
            .forEach(i -> df.addValue(i, record.get(i)));
      }

    } catch (IOException e) {
      Logger.getLogger(DataLoader.class.getName()).severe("Error reading CSV file: " + e.getMessage());
    }

    return df;
  }

  public void writeFile(DataFrame df, String filePath) {
    try (CSVPrinter printer = new CSVPrinter(new FileWriter(filePath), CSVFormat.DEFAULT)) {
      var columnNames = df.getColumnNames();

      String[] header = IntStream.range(0, columnNames.size())
          .mapToObj(i -> df.getColumn(i).getName())
          .toArray(String[]::new);
      printer.printRecord((Object[]) header);

      for(int i = 0; i < df.getRowCount(); i++) {
        String[] record = new String[columnNames.size()];

        for(int j = 0; j < columnNames.size(); j++)
          record[j] = df.getValue(j, i);
        printer.printRecord((Object[]) record);
      }
    } catch (IOException e) {
      Logger.getLogger(DataLoader.class.getName()).severe("Error writing to CSV file: " + e.getMessage());
    }
  }
}
