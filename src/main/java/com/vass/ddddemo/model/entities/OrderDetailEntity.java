package com.vass.ddddemo.model.entities;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailEntity {

  private Double amount;
  private String currency;
  private String plate;
  private Integer quantity;
}
