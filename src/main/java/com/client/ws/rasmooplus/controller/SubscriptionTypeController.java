package com.client.ws.rasmooplus.controller;



import com.client.ws.rasmooplus.dto.SubscriptionTypeDto;
import com.client.ws.rasmooplus.model.SubscriptionType;
import com.client.ws.rasmooplus.service.impl.SubscriptionTypeServiceImpl;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("subscription-type")
public class SubscriptionTypeController {


    @Autowired
    private SubscriptionTypeServiceImpl subscriptionTypeService;


    @Cacheable(value = "subscriptionType")
    @GetMapping()
    public ResponseEntity<List<SubscriptionType>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeService.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionType> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeService.findById(id));


    }


    @PostMapping
    public ResponseEntity<SubscriptionType> create(@RequestBody @Valid SubscriptionTypeDto dtoSubscriptionType) {
        SubscriptionType createdSubscriptionType = subscriptionTypeService.create(dtoSubscriptionType);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubscriptionType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionType> update(@PathVariable("id") Long id, @RequestBody SubscriptionTypeDto dtoSubscriptionType) {

        return  ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeService.update(id, dtoSubscriptionType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        subscriptionTypeService.delete(id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
