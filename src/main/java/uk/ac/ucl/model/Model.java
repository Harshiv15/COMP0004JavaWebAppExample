package uk.ac.ucl.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import uk.ac.ucl.DataFrame;
import uk.ac.ucl.DataLoader;

import static uk.ac.ucl.PatientHelpers.formatPatientInfo;

public class Model
{
  private DataFrame df;
  private final DataLoader dl;

  public Model() {
    df = new DataFrame();
    dl = new DataLoader();
  }

  public DataFrame getDataFrame() {
    return df;
  }

  public List<String> getPatientNames()
  {
    return IntStream.range(0, df.getRowCount())
        .mapToObj(i ->
            formatPatientInfo(df.getRow(i))
        )
        .collect(Collectors.toList());
  }

  public void readFile(String filePath)
  {
    df = dl.readFile(filePath);
  }

  public List<String> searchFor(String keyword)
  {
    // quite an inefficient search but couldn't add indexing in time
    List<String> results = new ArrayList<>();
    for(int i = 0; i < df.getColumnCount(); i++) {
      for(int row = 0; row < df.getRowCount(); row++) {
        var val = df.getValue(i, row);
        if(val.toLowerCase().contains(keyword.toLowerCase()))
          results.add(formatPatientInfo(df.getRow(row)));
      }
    }
    return results;
  }

  public void writeToFile(String filePath) {
    dl.writeFile(df, filePath);
  }
}
