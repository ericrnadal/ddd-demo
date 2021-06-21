package com.vass.ddddemo.model.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

  @Id
  private String id;
  private String tableNumber;
  private BigDecimal amount;
  private String currency;
  private boolean orderClosed;
  @ElementCollection(fetch = FetchType.EAGER)
  private List<OrderDetailEntity> orderDetailList;
}
