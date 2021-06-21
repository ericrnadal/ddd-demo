package com.vass.ddddemo.controllers;

import com.vass.ddddemo.mappers.OrderDTOMapper;
import com.vass.ddddemo.model.domain.Order;
import com.vass.ddddemo.model.dto.OrderDTO;
import com.vass.ddddemo.services.OrderJPAService;
import com.vass.ddddemo.services.OrderMongoService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/h2/order")
@RequiredArgsConstructor
public class OrderJPAController {

  private final OrderJPAService service;
  private final OrderDTOMapper mapper;

  @PostMapping
  public ResponseEntity add(@RequestBody OrderDTO orderDTO) {
    try {
      Order result = service.add(mapper.dtoToDomain(orderDTO));
      return new ResponseEntity<>(mapper.domainToDTO(result), HttpStatus.OK);
    } catch (IllegalArgumentException ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity add(@RequestBody OrderDTO orderDTO,
      @PathVariable("id") String id) {

    try {
      orderDTO.setId(UUID.fromString(id));
      Order result = service.modify(mapper.dtoToDomain(orderDTO));
      return new ResponseEntity<>(mapper.domainToDTO(result), HttpStatus.OK);
    } catch (IllegalArgumentException ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<OrderDTO> get(@PathVariable("id") String id) throws NotFoundException {

    try {
      Order result = service.get(UUID.fromString(id));
      return new ResponseEntity<>(mapper.domainToDTO(result), HttpStatus.OK);
    } catch (NotFoundException ex) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
