package com.vass.ddddemo.model.domain;

import com.vass.ddddemo.model.vo.Money;

public class OrderDetail {

  private Money price;
  private String plate;
  private Integer quantity;

  public OrderDetail(Money price, String plate, Integer quantity) {
    this.plate = plate;
    this.price = price;
    this.quantity = quantity;
  }

  public Money getDetailTotalPrice() {
    return new Money(price.getAmount() * quantity, price.getCurrency());
  }

  public Money getPrice() {
    return price;
  }

  public String getPlate() {
    return plate;
  }

  public Integer getQuantity() {
    return quantity;
  }
}
