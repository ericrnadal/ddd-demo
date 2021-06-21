package com.vass.ddddemo.model.vo;

import java.util.Objects;

public class Money {

  private final Double amount;
  private final Currency currency;

  public Money(Double amount, Currency currency) {
    if (Objects.isNull(amount)) {
      throw new IllegalArgumentException("Amount must be specified.");
    }
    this.amount = amount;
    this.currency = currency;
  }

  public Money sumMoney(Money money) {
    validateCurrency(money);
    return new Money(this.amount + money.getAmount(), currency);
  }

  public Money subtractMoney(Money money) {
    validateCurrency(money);
    return new Money(this.amount - money.getAmount(), currency);
  }

  private void validateCurrency(Money money) {
    if (!money.currency.getCurrency().equals(currency.getCurrency())) {
      throw new IllegalArgumentException("Money values must have the same currency");
    }
  }

  public Double getAmount() {
    return amount;
  }

  public Currency getCurrency() {
    return currency;
  }
}
