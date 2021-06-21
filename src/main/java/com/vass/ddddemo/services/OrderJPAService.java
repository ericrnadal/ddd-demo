package com.vass.ddddemo.services;

import com.vass.ddddemo.mappers.OrderEntityMapper;
import com.vass.ddddemo.model.domain.Order;
import com.vass.ddddemo.model.entities.OrderEntity;
import com.vass.ddddemo.repositories.JpaOrderRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderJPAService {

  private final JpaOrderRepository jpaOrderRepository;
  private final OrderEntityMapper mapper;

  public Order add(Order order){
    return mapper.entityToDomain(jpaOrderRepository.save(mapper.domainToEntity(order)));
  }

  public Order modify(Order order){
    return mapper.entityToDomain(jpaOrderRepository.save(mapper.domainToEntity(order)));
  }

  public Order get(UUID id) throws NotFoundException {
    Optional<OrderEntity> result = jpaOrderRepository.findById(id.toString());
    return mapper.entityToDomain(result.orElseThrow(() -> new NotFoundException()));
  }
}
