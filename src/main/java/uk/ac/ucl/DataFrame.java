package uk.ac.ucl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataFrame {
  private final ArrayList<Column> columns;

  public DataFrame() {
    this.columns = new ArrayList<>();
  }

  public void addColumn(Column column) {
    columns.add(column);
  }

  /**
   * @return a list of names in the same order as they are stored in the frame.
   */
  public ArrayList<String> getColumnNames() {
    return columns.stream()
        .map(Column::getName)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  /**
   * @return the number of rows in a column, all columns should have the same number
   * of rows when the frame is fully loaded with data.
   */
  public int getRowCount() {
    return columns.isEmpty() ? 0 : columns.getFirst().getSize();
  }

  /**
   * @param columnName
   * @param row
   * @return a row value from a column.
   */
  public String getValue(String columnName, int row) {
    return getColumn(columnName).getRowValue(row);
  }

  /**
   * @param columnIndex
   * @param row
   * @return a row value from a column.
   */
  public String getValue(int columnIndex, int row) {
    return getColumn(columnIndex).getRowValue(row);
  }

  /**
   * Puts a value into a row in a column.
   * @param columnName
   * @param row
   * @param value
   */
  public void putValue(String columnName, int row, String value) {
    getColumn(columnName).setRowValue(row, value);
  }

  /**
   * Puts a value into a row in a column.
   * @param columnIndex
   * @param row
   * @param value
   */
  public void putValue(int columnIndex, int row, String value) {
    getColumn(columnIndex).setRowValue(row, value);
  }

  /**
   * Adds a value to the end of a column.
   * @param columnName
   * @param value
   */
  public void addValue(String columnName, String value) {
    getColumn(columnName).addRowValue(value);
  }

  /**
   * Adds a value to the end of a column.
   * @param columnIndex
   * @param value
   */
  public void addValue(int columnIndex, String value) {
    getColumn(columnIndex).addRowValue(value);
  }

  /**
   * @param columnName
   * @return a reference to the first column with the given name.
   */
  public Column getColumn(String columnName) {
    return columns.stream()
        .filter(col -> col.getName().equals(columnName))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Column '" + columnName + "' not found"));
  }

  public Column getColumn(int columnIndex) {
    if(columnIndex >= getColumnCount())
      throw new ArrayIndexOutOfBoundsException("Column index out of bounds");
    return columns.get(columnIndex);
  }

  public int getColumnCount() {
    return columns.size();
  }

  public Map<String, String> getRow(int rowIndex) {
    Map<String, String> res = new HashMap<>();
    if(rowIndex >= getRowCount())
      throw new ArrayIndexOutOfBoundsException("Row index out of bounds");
    for(int i = 0; i < getColumnCount(); i++)
      res.put(columns.get(i).getName(), getValue(i, rowIndex));
    return res;
  }

  public void addRow(String... data) {
    if(data.length != getColumnNames().size())
      throw new IllegalArgumentException("Data length does not match row length");
    IntStream.range(0, getColumnCount())
        .forEach(i -> addValue(i, data[i]));
  }

  public void editRow(int rowIndex, String... data) {
    if(data.length != getColumnNames().size())
      throw new IllegalArgumentException("Data length does not match row length");
    IntStream.range(0, getColumnCount())
        .forEach(i -> putValue(i, rowIndex, data[i]));
  }

  public void deleteRow(int rowIndex) {
    columns.forEach(col -> col.removeRowValue(rowIndex));
  }
}
