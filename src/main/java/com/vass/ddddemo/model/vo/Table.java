package com.vass.ddddemo.model.vo;

import org.springframework.util.StringUtils;

public class Table {

  private final String tableNumber;

  public Table(String tableNumber) {
    if (StringUtils.isEmpty(tableNumber)) {
      throw new IllegalArgumentException("The table must be specified");
    }
    this.tableNumber = tableNumber;
  }

  public String getTableNumber() {
    return tableNumber;
  }
}
