package com.vass.ddddemo.repositories;

import com.vass.ddddemo.model.domain.Order;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoOrderRepository extends MongoRepository<Order, UUID> {

}
