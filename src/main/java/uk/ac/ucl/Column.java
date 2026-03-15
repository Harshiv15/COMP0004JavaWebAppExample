package uk.ac.ucl;

import java.util.ArrayList;
import java.util.List;

public class Column {
  private final String name;
  private final ArrayList<String> rows;

  public Column(String name) {
    this.name = name;
    this.rows = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public int getSize() {
    return rows.size();
  }

  public String getRowValue(int id) {
    if (id < 0 || id >= rows.size())
      throw new IllegalArgumentException("Row index out of bounds");
    return rows.get(id);
  }

  public void setRowValue(int id, String value) {
    rows.set(id, value);
  }

  public void addRowValue(String value) {
    rows.add(value);
  }

  public void removeRowValue(int id) {
    rows.remove(id);
  }
}
