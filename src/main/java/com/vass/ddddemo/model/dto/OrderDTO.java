package com.vass.ddddemo.model.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

  private UUID id;
  private String tableNumber;
  private BigDecimal amount;
  private String currency;
  private boolean orderClosed;
  private List<OrderDetailDTO> orderDetails;
}
