package com.vass.ddddemo.mappers;

import com.vass.ddddemo.model.domain.Order;
import com.vass.ddddemo.model.domain.OrderDetail;
import com.vass.ddddemo.model.dto.OrderDTO;
import com.vass.ddddemo.model.dto.OrderDetailDTO;
import com.vass.ddddemo.model.vo.Currency;
import com.vass.ddddemo.model.vo.Money;
import com.vass.ddddemo.model.vo.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.ObjectUtils;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface OrderDTOMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "table.tableNumber", target = "tableNumber")
  @Mapping(source = "totalAmount.amount", target = "amount")
  @Mapping(source = "totalAmount.currency.currency", target = "currency")
  @Mapping(source = "orderClosed", target = "orderClosed")
  @Mapping(source = "orderDetailList", target = "orderDetails", qualifiedByName = "orderDetailListConverter")
  OrderDTO domainToDTO(Order order);

  @Named("orderDetailListConverter")
  default List<OrderDetailDTO> orderDetailListConverter(List<OrderDetail> orderDetailList) {

    List<OrderDetailDTO> listOrderDetailEntities = new ArrayList<>();
    if (orderDetailList != null && !orderDetailList.isEmpty()) {
      for (OrderDetail orderDetail : orderDetailList) {
        OrderDetailDTO orderDetailEntity = orderDetailToOrderDetailDTO(orderDetail);
        listOrderDetailEntities.add(orderDetailEntity);
      }
    }
    return listOrderDetailEntities;
  }

  @Mapping(source = "price.amount", target = "amount")
  @Mapping(source = "price.currency.currency", target = "currency")
  @Mapping(source = "plate", target = "plate")
  @Mapping(source = "quantity", target = "quantity")
  OrderDetailDTO orderDetailToOrderDetailDTO(OrderDetail orderDetail);

  default Order dtoToDomain(OrderDTO orderDTO) {

    Table table = new Table(orderDTO.getTableNumber());
    Order order =
        new Order(ObjectUtils.isEmpty(orderDTO.getId()) ? UUID.randomUUID() : orderDTO.getId(), table);

    for (OrderDetailDTO orderDetailDTO : orderDTO.getOrderDetails()) {
      Currency currency = new Currency(orderDetailDTO.getCurrency());
      Money money = new Money(orderDetailDTO.getAmount(), currency);
      order.addOrderDetail(money, orderDetailDTO.getPlate(), orderDetailDTO.getQuantity());
    }

    return order;
  }
}
