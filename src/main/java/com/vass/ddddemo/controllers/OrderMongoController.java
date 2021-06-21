package com.vass.ddddemo.controllers;

import com.vass.ddddemo.model.dto.OrderDTO;
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
@RequestMapping("/mongo/order")
@RequiredArgsConstructor
public class OrderMongoController {

  private final OrderMongoService service;

  @PostMapping
  public ResponseEntity add(@RequestBody OrderDTO orderDTO) {
    try {
      OrderDTO result = service.add(orderDTO);
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (IllegalArgumentException ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity add(@RequestBody OrderDTO orderDTO,
      @PathVariable("id") String id) {
    try {
      orderDTO.setId(UUID.fromString(id));
      OrderDTO result = service.modify(orderDTO);
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (IllegalArgumentException ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<OrderDTO> get(@PathVariable("id") String id) {

    try {
      OrderDTO result = service.get(UUID.fromString(id));
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (NotFoundException ex) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
