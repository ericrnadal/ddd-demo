package com.vass.ddddemo.services;

import com.vass.ddddemo.mappers.OrderDTOMapper;
import com.vass.ddddemo.model.domain.Order;
import com.vass.ddddemo.model.dto.OrderDTO;
import com.vass.ddddemo.repositories.MongoOrderRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderMongoService {

  private final MongoOrderRepository mongoOrderRepository;
  private final OrderDTOMapper mapper;

  public OrderDTO add(OrderDTO orderDTO){
    return mapper.domainToDTO(mongoOrderRepository.save(mapper.dtoToDomain(orderDTO)));
  }

  public OrderDTO modify(OrderDTO orderDTO){
    return mapper.domainToDTO(mongoOrderRepository.save(mapper.dtoToDomain(orderDTO)));
  }

  public OrderDTO get(UUID id) throws NotFoundException {
    Optional<Order> result = mongoOrderRepository.findById(id);
    return mapper.domainToDTO(result.orElseThrow(() -> new NotFoundException()));
  }
}
