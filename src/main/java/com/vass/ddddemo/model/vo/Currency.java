package com.vass.ddddemo.model.vo;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class Currency {

  private final String currency;

  public Currency(String currency) {
    if (ObjectUtils.isEmpty(currency)) {
      throw new IllegalArgumentException("Money values must be specified.");
    }
    if (StringUtils.isEmpty(currency)) {
      throw new IllegalArgumentException("Money values must have the same currency.");
    }
    this.currency = currency;
  }

  public String getCurrency() {
    return currency;
  }
}
