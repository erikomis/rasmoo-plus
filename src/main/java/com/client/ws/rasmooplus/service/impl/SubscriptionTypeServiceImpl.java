package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.dto.SubscriptionTypeDto;
import com.client.ws.rasmooplus.exception.NotFoudException;
import com.client.ws.rasmooplus.model.SubscriptionType;
import com.client.ws.rasmooplus.repository.SubscriptionTypeRepository;
import com.client.ws.rasmooplus.service.SubscriptionTypeService;
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
    public List<SubscriptionType> findAll() {
        return this.subscriptionTypeRepository.findAll();
    }

    @Override
    public SubscriptionType findById(Long id) {
        return getSubscriptionType(id);
    }

    @Override
    public SubscriptionType create(SubscriptionTypeDto subscriptionType) {

        if (Objects.nonNull(subscriptionType.getId())){
            throw new NotFoudException("Id deve ser nulo");
        }

        return subscriptionTypeRepository.save(SubscriptionType.builder()
                .id(subscriptionType.getId())
                .name(subscriptionType.getName())
                .AccessMonth(subscriptionType.getAccessMonth())
                .price(subscriptionType.getPrice())
                .productKey(subscriptionType.getProductKey())
                .build());
    }

    @Override
    public SubscriptionType update(Long id, SubscriptionTypeDto subscriptionType) {

        SubscriptionType oldSubscriptionType = getSubscriptionType(id);



        return subscriptionTypeRepository.save(SubscriptionType.builder()
                .id(id)
                .name(subscriptionType.getName() != null ? subscriptionType.getName() : oldSubscriptionType.getName())
                .AccessMonth(subscriptionType.getAccessMonth() !=null ? subscriptionType.getAccessMonth() : oldSubscriptionType.getAccessMonth())
                .price(subscriptionType.getPrice() != null ? subscriptionType.getPrice() : oldSubscriptionType.getPrice())
                .productKey(subscriptionType.getProductKey())
                .build());
    }

    @Override
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
