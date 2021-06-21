package com.vass.ddddemo.model.domain;

import com.vass.ddddemo.model.vo.Currency;
import com.vass.ddddemo.model.vo.Money;
import com.vass.ddddemo.model.vo.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Order {

  private UUID id;
  private Table table;
  private Money totalAmount;
  private boolean orderClosed;
  private List<OrderDetail> orderDetailList;

  public Order(UUID id, Table table) {
    this.id = id;
    this.table =  table;
    this.totalAmount = new Money(0d, new Currency("EUR"));
    this.orderDetailList = new ArrayList<>();
  }

  public void addOrderDetail(Money price, String plate, Integer quantity) {
    if (orderClosed) {
      //Throw custom exception
    } else {
      OrderDetail orderDetail = new OrderDetail(price, plate, quantity);
      totalAmount = totalAmount.sumMoney(orderDetail.getDetailTotalPrice());
      orderDetailList.add(orderDetail);
    }
  }

  public void calculateTotalAmount() {
      totalAmount = new Money(orderDetailList.stream().mapToDouble(orderDetail -> orderDetail.getDetailTotalPrice().getAmount()).sum(), totalAmount.getCurrency());
  }

  public void closeOrder() {
    orderClosed = true;
  }

  public void setTable(Table table){
    if (orderClosed) {
      //Throw custom exception
    } else  {
      this.table = table;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Order order = (Order) o;

    return id.equals(order.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  public UUID getId() {
    return id;
  }

  public Table getTable() {
    return table;
  }

  public Money getTotalAmount() {
    return totalAmount;
  }

  public boolean isOrderClosed() {
    return orderClosed;
  }

  public List<OrderDetail> getOrderDetailList() {
    return orderDetailList;
  }
}

