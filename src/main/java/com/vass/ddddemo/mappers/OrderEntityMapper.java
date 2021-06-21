package com.vass.ddddemo.mappers;

import com.vass.ddddemo.model.domain.Order;
import com.vass.ddddemo.model.domain.OrderDetail;
import com.vass.ddddemo.model.entities.OrderDetailEntity;
import com.vass.ddddemo.model.entities.OrderEntity;
import com.vass.ddddemo.model.vo.Currency;
import com.vass.ddddemo.model.vo.Money;
import com.vass.ddddemo.model.vo.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface OrderEntityMapper {

  @Mapping(expression = "java(order.getId() == null ? UUID.randomUUID().toString() : order.getId().toString() )", target = "id")
  @Mapping(source = "table.tableNumber", target = "tableNumber")
  @Mapping(source = "totalAmount.amount", target = "amount")
  @Mapping(source = "totalAmount.currency.currency", target = "currency")
  @Mapping(source = "orderClosed", target = "orderClosed")
  @Mapping(source = "orderDetailList", target = "orderDetailList", qualifiedByName = "orderDetailListConverter")
  OrderEntity domainToEntity(Order order);

  @Named("orderDetailListConverter")
  default List<OrderDetailEntity> orderDetailListConverter(List<OrderDetail> orderDetailList) {

    List<OrderDetailEntity> listOrderDetailEntities = new ArrayList<>();
    if (orderDetailList != null && !orderDetailList.isEmpty()) {
      for (OrderDetail orderDetail : orderDetailList) {
        OrderDetailEntity orderDetailEntity = entityOrderItemsToOrderItemsDTO(orderDetail);
        listOrderDetailEntities.add(orderDetailEntity);
      }
    }
    return listOrderDetailEntities;
  }

  @Mapping(source = "price.amount", target = "amount")
  @Mapping(source = "price.currency.currency", target = "currency")
  @Mapping(source = "plate", target = "plate")
  @Mapping(source = "quantity", target = "quantity")
  OrderDetailEntity entityOrderItemsToOrderItemsDTO(OrderDetail orderDetail);

  default Order entityToDomain(OrderEntity orderEntity) {

    Table table = new Table(orderEntity.getTableNumber());
    Order order = new Order(UUID.fromString(orderEntity.getId()), table);

    for (OrderDetailEntity orderDetailEntity : orderEntity.getOrderDetailList()) {
      Currency currency = new Currency(orderDetailEntity.getCurrency());
      Money money = new Money(orderDetailEntity.getAmount(), currency);
      order.addOrderDetail(money, orderDetailEntity.getPlate(), orderDetailEntity.getQuantity());
    }

    return order;
  }
}
