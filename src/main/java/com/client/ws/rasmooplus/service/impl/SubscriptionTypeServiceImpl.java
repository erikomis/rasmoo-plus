package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.controller.SubscriptionTypeController;
import com.client.ws.rasmooplus.dto.SubscriptionTypeDto;
import com.client.ws.rasmooplus.exception.NotFoudException;
import com.client.ws.rasmooplus.mapper.SubscriptionTypeMapper;
import com.client.ws.rasmooplus.model.jpa.SubscriptionType;
import com.client.ws.rasmooplus.repository.jpa.SubscriptionTypeRepository;
import com.client.ws.rasmooplus.service.SubscriptionTypeService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    private final SubscriptionTypeRepository subscriptionTypeRepository;

    public SubscriptionTypeServiceImpl(SubscriptionTypeRepository subscriptionTypeRepository) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }


    @Override
    @Cacheable(value = "subscriptionType")
    public List<SubscriptionType> findAll() {
        return this.subscriptionTypeRepository.findAll();
    }
    @Cacheable(value = "subscriptionType" , key = "#id")
    @Override
    public SubscriptionType findById(Long id) {
        return getSubscriptionType(id).add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class).findById(id)
                ).withSelfRel()
        ).add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class).update(id,new SubscriptionTypeDto() )
                ).withRel("update")
        ).add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class).delete(id)
                ).withRel("delete")
        );
    }

    @CacheEvict(value = "subscriptionType", allEntries = true)
    @Override
    public SubscriptionType create(SubscriptionTypeDto subscriptionType) {

        if (Objects.nonNull(subscriptionType.getId())) {
            throw new NotFoudException("Id deve ser nulo");
        }

        return subscriptionTypeRepository.save(SubscriptionTypeMapper.fromDtoToEntity(subscriptionType));
    }

    @Override
    @CacheEvict(value = "subscriptionType", allEntries = true)
    public SubscriptionType update(Long id, SubscriptionTypeDto subscriptionType) {


        getSubscriptionType(id);

        subscriptionType.setId(id);


        return subscriptionTypeRepository.save(SubscriptionTypeMapper.fromDtoToEntity(subscriptionType));
    }

    @Override
    @CacheEvict(value = "subscriptionType", allEntries = true)
    public void delete(Long id) {

        getSubscriptionType(id);

        subscriptionTypeRepository.deleteById(id);
    }


    private SubscriptionType getSubscriptionType(Long id) {
        Optional<SubscriptionType> subscriptionType = this.subscriptionTypeRepository.findById(id);

        if (subscriptionType.isEmpty()) {
            throw new NotFoudException("Subscription type not found");
        }
        return subscriptionType.get();
    }
}
