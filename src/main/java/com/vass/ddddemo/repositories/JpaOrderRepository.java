package com.vass.ddddemo.repositories;

import com.vass.ddddemo.model.entities.OrderEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, String> {

}
